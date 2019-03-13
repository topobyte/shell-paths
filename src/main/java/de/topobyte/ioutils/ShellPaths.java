package de.topobyte.ioutils;

import java.nio.file.Path;
import java.nio.file.Paths;

import de.topobyte.system.utils.SystemPaths;

public class ShellPaths {

	/**
	 * Convert a path specification to a {@link Path} instance. A {@link Path} is
	 * initially created using {@link Paths#get(String, String...) Paths.get()}. If
	 * that path is absolute it is returned. Otherwise, the behavior is as follows.
	 * If the first component of the path is '~', then the path is resolved against
	 * the user's home directory. Otherwise the path is resolved against the current
	 * working directory of the executing process.
	 * 
	 * @param pathSpec an absolute or relative path specification.
	 * @return the resolved {@link Path}.
	 */
	public static Path resolve(String pathSpec) {
		Path resolved = null;
		Path path = Paths.get(pathSpec);
		if (path.isAbsolute()) {
			resolved = path;
		} else {
			String firstPart = path.getName(0).toString();
			if (firstPart.equals("~")) {
				resolved = SystemPaths.HOME.resolve(path.subpath(1, path.getNameCount()));
			} else {
				resolved = SystemPaths.CWD.resolve(path);
			}
		}
		return resolved;
	}

}
