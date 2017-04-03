package org.bettelle.neon.is.qa.int1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import org.bettelle.neon.is.qa.prop.GenericEnv;

/**
 * INT: http://int-cds-llb-1.ci.neoninternal.org/cdsWebApp
 */
@Component
public class int1Env implements GenericEnv {

	private String envName = "int1";

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
		return "Int1Env [envName=" + envName + ", profileName=" + profileName + "]";
	}
}
