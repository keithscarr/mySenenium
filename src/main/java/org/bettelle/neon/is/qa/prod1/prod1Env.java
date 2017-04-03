package org.bettelle.neon.is.qa.prod1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.bettelle.neon.is.qa.prop.GenericEnv;

/**
 * DEV: http://qa-as-cds-1.ci.neoninternal.org:8080/cdsWebApp
 */
@Component
public class prod1Env implements GenericEnv {
	
	private String envName = "prod1";
	
	@Value("${profile.name}")
	private String profileName;

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	@Override
	public String toString() {
		return "prod1Env [envName=" + envName + ", profileName=" + profileName + "]";
	}

}
