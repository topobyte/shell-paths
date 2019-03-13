// Copyright 2019 Sebastian Kuerten
//
// This file is part of shell-paths.
//
// shell-paths is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// shell-paths is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with shell-paths. If not, see <http://www.gnu.org/licenses/>.

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
