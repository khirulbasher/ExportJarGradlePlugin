package com.lemon.framework.gradleplugin.extension.executable;

import org.gradle.api.Project;

import static com.lemon.framework.gradleplugin.task.Executable.DEFAULT_CONFIG;

@SuppressWarnings("unused")
public class ExportJarExtension {
    public char separator = '/';
    public String exportLocation = "export";
    public String dependencyLocation = "library";
    public String exclude = "'META-INF/*.SF', 'META-INF/*.DSA','META-INF/*.RSA','META-INF/*.MF'";
    private Project project;

    public ExportJarExtension(Project project) {
        this.project = project;
    }

    public String dependencies() {
        StringBuilder builder = new StringBuilder();
        project.getConfigurations().getByName(DEFAULT_CONFIG).forEach(dependency -> builder.append(dependencyLocation).append(separator).append(dependency.getName()).append(" "));
        return builder.toString();
    }
}
