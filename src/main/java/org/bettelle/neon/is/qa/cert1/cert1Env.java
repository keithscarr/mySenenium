package org.bettelle.neon.is.qa.cert1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.bettelle.neon.is.qa.prop.GenericEnv;

/**
 * CERT1: http://cert-cds-llb-1.ci.neoninternal.org/cdsWebApp
 */
@Component
public class cert1Env implements GenericEnv {

	private String envName = "cert1";

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
		return "cert1Env [envName=" + envName + ", profileName=" + profileName + "]";
	}

}
