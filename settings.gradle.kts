pluginManagement {
	repositories {
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net/")
		}
		maven {
			name = "BlameJared"
			url = uri("https://maven.blamejared.com/")
		}
		mavenCentral()
		gradlePluginPortal()
	}
}