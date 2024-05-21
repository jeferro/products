package com.jeferro.products.shared.infrastructure.adapters.utils;

import java.util.Arrays;

import org.approvaltests.Approvals;

public abstract class ApprovalUtils {

  public static void verifyAll(Object... params) {
	var array = Arrays.stream(params)
		.map(Object::toString)
		.toArray();

	Approvals.verifyAll("params", array);
  }
}
