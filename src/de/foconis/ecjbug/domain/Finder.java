
package de.foconis.ecjbug.domain;

import javax.annotation.Nonnull;

import de.foconis.ecjbug.domain.query.QEntity1;
import de.foconis.ecjbug.domain.query.QEntity2;

public class Finder {

	//	add that block and it will compile
	//	@Nonnull
	//	public static QEntity1 getQuery1() {
	//		return new QEntity1();
	//	}

	@Nonnull
	public static QEntity2 getQuery2() {
		return new QEntity2();
	}

}
