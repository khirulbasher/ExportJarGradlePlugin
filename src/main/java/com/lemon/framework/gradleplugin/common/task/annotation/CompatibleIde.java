package com.lemon.framework.gradleplugin.common.task.annotation;

import com.lemon.framework.gradleplugin.common.task.CommonTask;
import com.lemon.framework.gradleplugin.extension.plugin.CompatibleExtension;
import org.gradle.api.Project;
import org.gradle.api.Task;

public class CompatibleIde implements CommonTask {
    private final Project project;

    public CompatibleIde(Project project) {
        this.project = project;
        apply(project);
    }

    @Override
    public void apply(Project project) {
        Task build = project.getTasks().findByName("build");
        CompatibleExtension extension = project.getExtensions().create("compatible", CompatibleExtension.class, project);
        if (build != null) build.doLast(c -> {
            extension.makeCompatible();
        });
    }
}
