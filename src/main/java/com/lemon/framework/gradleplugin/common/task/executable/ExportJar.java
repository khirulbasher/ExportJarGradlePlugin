package com.lemon.framework.gradleplugin.common.task.executable;

import com.lemon.framework.gradleplugin.common.task.CommonTask;
import com.lemon.framework.gradleplugin.extension.executable.ExportJarExtension;
import org.gradle.api.Project;
import org.gradle.api.Task;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import static com.lemon.framework.gradleplugin.task.Executable.DEFAULT_CONFIG;

public class ExportJar implements CommonTask {
    private final Project project;

    public ExportJar(Project project) {
        this.project = project;
        apply(project);
    }

    @Override
    public void apply(Project project) {
        ExportJarExtension extension = project.getExtensions()
                .create("exportJar", ExportJarExtension.class, project);

        Task build = project.getTasks().findByName("build");

        Task exportJar = project.task("exportJar")
                .dependsOn(build);
        exportJar.doLast(t -> {
            final File dir = new File(extension.exportLocation, extension.dependencyLocation);
            if (!dir.exists()) dir.mkdirs();
            project.getConfigurations().getByName(DEFAULT_CONFIG).forEach(config -> {
                try (DataOutputStream outStream = new DataOutputStream(new FileOutputStream(new File(dir, config.getName())))) {
                    Files.copy(config.toPath(), outStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            String appName = project.getName() + "-" + project.getVersion() + ".jar";
            try (DataOutputStream outStream = new DataOutputStream(new FileOutputStream(new File(dir.getParent(), appName)))) {
                Files.copy(new File(project.getBuildDir(), new StringBuilder().append("libs").append(extension.separator).append(appName).toString()).toPath(), outStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
