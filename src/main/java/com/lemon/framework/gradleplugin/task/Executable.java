package com.lemon.framework.gradleplugin.task;

import com.lemon.framework.gradleplugin.common.task.executable.ExportJar;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

@SuppressWarnings({"StringBufferReplaceableByString", "ResultOfMethodCallIgnored", "NullableProblems"})
public class Executable implements Plugin<Project> {
    public static final String DEFAULT_CONFIG = "default";

    @Override
    public void apply(Project project) {
        new ExportJar(project);
    }
}
