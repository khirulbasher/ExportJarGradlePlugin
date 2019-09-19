package com.lemon.framework.gradleplugin.task.compatible;

import com.lemon.framework.gradleplugin.common.task.annotation.CompatibleIde;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class Annotation implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        new CompatibleIde(project);
    }
}
