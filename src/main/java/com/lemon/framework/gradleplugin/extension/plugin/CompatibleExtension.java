package com.lemon.framework.gradleplugin.extension.plugin;

import org.gradle.api.Project;

import java.io.*;
import java.nio.file.Files;

@SuppressWarnings({"WeakerAccess", "ResultOfMethodCallIgnored", "StringBufferReplaceableByString"})
public class CompatibleExtension {
    public static final String IDE_INTELLIJ = "idea";
    private static final String INTELLIJ_COMPATIBLE_PATH = "out" + File.separator + "production" + File.separator + "classes";
    private static final String META_INF = File.separator + "META-INF";
    private static final String SERVICES = File.separator + "services";
    private final Project project;
    public String ideName = IDE_INTELLIJ;

    public String compatiblePath = "";

    public CompatibleExtension(Project project) {
        this.project = project;
    }

    public void makeCompatible() {
        switch (ideName) {
            case CompatibleExtension.IDE_INTELLIJ:
                makeCompatible(INTELLIJ_COMPATIBLE_PATH);
        }

        if (!compatiblePath.isEmpty()) makeCompatible(compatiblePath);
    }

    private void makeCompatible(String compatiblePath) {
        File dir = new File(compatiblePath);
        File out = new File(new File(dir, META_INF), SERVICES);
        if (!out.exists()) out.mkdirs();
        StringBuilder builder = new StringBuilder().append(project.getBuildDir()).append(File.separator).append("classes").append(File.separator).append("java")
                .append(File.separator).append("main").append(File.separator).append("META-INF");
        File sourceFile = new File(builder.toString());
        if (!sourceFile.exists()) return;
        try {
            File[] metas = sourceFile.listFiles();
            if (metas != null)
                for (File file : metas) {
                    if (file.isDirectory()) continue;
                    try (OutputStream outStream = new DataOutputStream(new FileOutputStream(new File(new File(dir, META_INF), file.getName())))) {
                        Files.copy(file.toPath(), outStream);
                    }
                }

            File[] services = new File(sourceFile, SERVICES).listFiles();
            if (services != null)
                for (File file : services) {
                    if (file.isDirectory()) continue;
                    try (OutputStream outStream = new DataOutputStream(new FileOutputStream(new File(new File(new File(dir, META_INF), SERVICES), file.getName())))) {
                        Files.copy(file.toPath(), outStream);
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
