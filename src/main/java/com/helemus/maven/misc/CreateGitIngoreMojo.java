package com.helemus.maven.misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.codehaus.plexus.util.IOUtil;

/**
 * Goal which sets up a basic .gitignore file
 *
 * @goal create-gitignore
 */
public class CreateGitIngoreMojo extends AbstractMojo {

    /**
     * Location of the base directory.
     *
     * @parameter expression="${basedir}"
     * @required
     */
    private File basedir;

    public void execute() throws MojoExecutionException, MojoFailureException {
        File ignoreFile = new File(basedir, ".gitignore");
        if (!ignoreFile.exists()) {
            PrintWriter w = null;
            try {
                w = new PrintWriter(new FileWriter(ignoreFile));
                w.println("target/");
                w.println(".settings/");
                w.println(".classpath");
                w.println(".project");
            } catch (IOException e) {
                throw new MojoExecutionException("unable to write .gitignore", e);
            } finally {
                IOUtil.close(w);
            }
        } else {
            getLog().warn("not going to overwrite the existing gitignore file");
        }
    }

}
