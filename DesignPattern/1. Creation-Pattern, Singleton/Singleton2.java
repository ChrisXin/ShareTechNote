// only one instance of a class, Global point of access

public class SecurityManager() {

private SecurityManager instance;

private SecurityManager() {/* */}

public SecurityManager getInstance() {
	if (instance == null) {
		instance = new SecurityManager();
		}
		return instance;
	}
}