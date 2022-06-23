package com.rasime.mymojos;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Developer;
import org.apache.maven.model.Plugin;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;



@Mojo(name="summarize", defaultPhase = LifecyclePhase.INSTALL)
public class MojoDeneme extends AbstractMojo {
	
	@Parameter(defaultValue = "${project}",required = true)
	private MavenProject project;
 
	@Parameter(defaultValue = "${project.build.directory}", required = true)
	private File outputFile;
	
	@Parameter(property = "release.date",required = true)
	private String releaseDate;
	
	
	
 
	public void execute() throws MojoExecutionException, MojoFailureException {
 
		String str="Project info:"+project.getGroupId()+"."+project.getArtifactId()+"."+project.getVersion()+"\n";
		List<Developer> developer=project.getDevelopers();		
		List<Dependency> dependencies=project.getDependencies();
		List<Plugin> build = project.getBuildPlugins();
		
		
		
		
		outputFile=new  File("target/output.txt");
		try {
			if(outputFile.exists()) {
			   getLog().info("klasör plugingi import eden projede target klasörün altında var.");
			}
			else {
			    outputFile.createNewFile(); 
			    getLog().info("klasör plugingi import eden projede target klasörün altında oluşturuldu.");
			}
			
	        FileWriter fileWriter = new FileWriter(outputFile, false);
	        BufferedWriter bWriter = new BufferedWriter(fileWriter);
	        bWriter.write(str);
	        bWriter.write("Developers:\n");
	        for(Developer d: developer) {
				bWriter.write("\tDeveloper "+d.getId()+" Name : "+d.getName()+"\n");
			}
	        bWriter.write("Dependecies:\n");
	        for (Dependency dependency : dependencies) {
				bWriter.write("\tDependency "+dependency.getGroupId()+"."+dependency.getArtifactId()+"\n");
			}
	        bWriter.write("Release Date: "+releaseDate.toString()+"\n");
	        
	        bWriter.write("Plugins\n");
	        for (Plugin current : build) {
	            
	            bWriter.write("\tPlugin: "+current.getArtifactId()+"\n");
	          }
	        bWriter.close();
	       
		} catch (IOException e) {
		    System.err.println(e);
		}
		
		
 
	}


	

	public MojoDeneme() {
		super();
	}




	public MojoDeneme(MavenProject project, File outputFile, String releaseDate) {
		super();
		this.project = project;
		this.outputFile = outputFile;
		this.releaseDate = releaseDate;
	}




	public MavenProject getProject() {
		return project;
	}




	public void setProject(MavenProject project) {
		this.project = project;
	}




	public File getOutputFile() {
		return outputFile;
	}




	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}




	public String getReleaseDate() {
		return releaseDate;
	}




	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
 
}