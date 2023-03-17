package fit.wenchao.kotlinplayground.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * This scanner is used to find out all classes in a package.
 */
@Slf4j
public class ClasspathPackageScanner {

    private String basePackage;
    private ClassLoader cl;

    /**
     * Construct an instance with base package and class loader.
     *
     * @param basePackage The base package to scan.
     */
    public ClasspathPackageScanner(String basePackage) {
        this.basePackage = basePackage;
        this.cl = getClass().getClassLoader();
    }

    /**
     * Get all fully qualified names located in the specified package
     * and its sub-package.
     *
     * @return A list of fully qualified names.
     */
    public List<String> getFullyQualifiedClassNameList() throws IOException {
        return doScan(basePackage, new ArrayList<>());
    }

    /**
     * "file:/home/wc/cn/foo" -> "/home/wc/cn/foo"
     * "jar:file:/home/wc/foo.jar!/cn/fh" -> "/home/wc/foo.jar"
     */
    private static String getPackPath(URL url) {
        if (url == null) {
            return "";
        }
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        // is a directory url
        if (-1 == pos) {
            return fileUrl;
        }

        // is a jar url, we get the jar name: file:***.jar!/**/classes/you/package -> ***.jar
        return fileUrl.substring(5, pos);
    }

    /**
     * "cn.fh.lightning" -> "cn/fh/lightning"
     */
    private static String dotToSplash(String name) {
        return name.replaceAll("\\.", "/");
    }

    private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
        List<String> nameList = new ArrayList<>();
        try (JarInputStream jarIn = new JarInputStream(Files.newInputStream(Paths.get(jarPath)));) {
            JarEntry jarEntry = jarIn.getNextJarEntry();
            while (null != jarEntry) {
                String name = jarEntry.getName();
                if (name.contains(splashedPackageName) && isClassFile(name)) {
                    nameList.add(name);
                }

                jarEntry = jarIn.getNextJarEntry();
            }

            return nameList;
        }
    }

    /**
     * "Apple.class" -> "Apple"
     */
    private static String trimExtension(String name) {
        int pos = name.indexOf('.');
        if (-1 != pos) {
            return name.substring(0, pos);
        }

        return name;
    }

    private boolean isClassFile(String name) {
        return name.endsWith(".class");
    }

    /**
     * Actually perform the scanning procedure.
     *
     * @param basePackage Target package
     * @param nameList    A list to contain the result.
     * @return A list of fully qualified names.
     */
    private List<String> doScan(String basePackage, List<String> nameList) throws IOException {

        // replace dots with splashes
        String splashPath = dotToSplash(basePackage);

        // get file path
        String jarNameOrDirectoryPath = getPackPath(cl.getResource(splashPath));

        log.debug("package location：{}", jarNameOrDirectoryPath);

        // Get classes in that package.
        // If the web server unzips the jar file, then the classes will exist in
        // the form of normal file in the directory.
        // If the web server does not unzip the jar file, then classes will exist
        // in jar file.
        List<String> names = null; // contains the name of the class file.

        if (isJar(jarNameOrDirectoryPath)) {
            names = readFromJarFile(jarNameOrDirectoryPath, splashPath);
        }
        else {
            names = readFromDirectory(jarNameOrDirectoryPath);
        }

        // convert classfile name into class name. e.g: Apple.class will be stored as "Apple"
        for (String name : names) {
            if (isClassFile(name)) {
                int i = name.lastIndexOf("/");
                if (i != -1) {
                    name = name.substring(i + 1);
                }
                //nameList.add(basePackage + "." + StringUtil.trimExtension(name));
                nameList.add(toFullyQualifiedName(name, basePackage));
            }
            else {
                // directory, recursively check this directory for more classes
                doScan(basePackage + "." + name, nameList);
            }
        }

        return nameList;
    }

    /**
     * Convert short class name to fully qualified name.
     * e.g., String -> java.lang.String
     */
    private String toFullyQualifiedName(String shortName, String basePackage) {
        return basePackage + '.' + trimExtension(shortName);
    }

    private List<String> readFromDirectory(String path) {
        File file = new File(path);
        String[] names = file.list();

        if (null == names) {
            return new ArrayList<>();
        }

        return Arrays.asList(names);
    }

    private boolean isJar(String name) {
        if (name == null) {
            return false;
        }
        return name.endsWith(".jar");
    }

}