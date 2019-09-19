package com.lemon.framework.gradleplugin;

import com.lemon.framework.gradleplugin.common.task.annotation.CompatibleIde;
import com.lemon.framework.gradleplugin.common.task.executable.ExportJar;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class Framework implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        new ExportJar(project);
        new CompatibleIde(project);
    }
}
