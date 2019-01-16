/**
 * 
 */
package com.silentlakeside.study.algorithm.linkedlist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author zhongqi.lin
 *
 */
public class OneWayLinkedListTest {

	private abstract class Action {

		void executeAndCheck(List<String> reference, OneWayLinkedList<String> testObject) {
			execute(reference, testObject);
			check(reference, testObject);
		}

		abstract void execute(List<String> reference, OneWayLinkedList<String> testObject);

		private void check(List<String> reference, OneWayLinkedList<String> testObject) {
			String errorMessage = "Failed at action " + toString();
			assertEquals(errorMessage, reference.size(), testObject.size());
			for (int i = 0; i < reference.size(); i++) {
				assertEquals(errorMessage, reference.get(i), testObject.get(i));
			}
		}
	}

	private class AddAction extends Action {
		String value;

		AddAction(String value) {
			this.value = value;
		}

		public void execute(List<String> reference, OneWayLinkedList<String> testObject) {
			testObject.add(value);
			reference.add(value);
		}

		public String toString() {
			return "AddAction(" + value + ")";
		}
	}

	private class RemoveAction extends Action {
		int index;

		RemoveAction(int index) {
			this.index = index;
		}

		public void execute(List<String> reference, OneWayLinkedList<String> testObject) {
			testObject.remove(index);
			reference.remove(index);
		}

		public String toString() {
			return "RemoveAction(" + index + ")";
		}
	}

	@Test
	public void testBasicFunctions() {
		List<String> reference = new ArrayList<String>();
		OneWayLinkedList<String> testObject = new OneWayLinkedList<String>();

		List<Action> actions = new ArrayList<Action>();
		actions.add(new AddAction("a"));
		actions.add(new AddAction("2"));
		actions.add(new AddAction("abc"));
		actions.add(new RemoveAction(2));
		actions.add(new RemoveAction(0));
		actions.add(new RemoveAction(0));

		assertEquals("Initial state", reference.size(), testObject.size());
		for (Action action : actions) {
			action.executeAndCheck(reference, testObject);
		}
	}

}
