package com.silentlakeside.study.algorithm.linkedlist;

/**
 * @author zhongqi.lin
 *
 */
public class OneWayLinkedList<T> {

	private class Element {
		Element next;
		T value;
	}

	private Element head;
	private Element end;
	private int size = 0;

	public OneWayLinkedList<T> add(T obj) {
		if (head == null) {
			head = new Element();
			head.value = obj;
			end = head;
		} else {
			// create new end element
			Element newEnd = new Element();
			newEnd.value = obj;
			// old end element refers to new end element
			end.next = newEnd;
			// new end element becomes current end element
			end = newEnd;
		}
		size++;

		return this;
	}

	public OneWayLinkedList<T> remove(int index) {
		validateIndex(index);

		if (index == 0) { // if to remove the head
			Element newHead = head.next;
			// clear reference
			head.next = null;
			head = newHead;
		} else {
			Element prevElement = findByIndex(index - 1);
			Element curElement = prevElement.next;
			Element nextElement = curElement.next;

			prevElement.next = nextElement;
			// if the element to be removed is the end element
			if (nextElement == null) {
				end = prevElement;
			}
			// clear reference
			curElement.next = null;
		}

		size--;

		return this;
	}

	public T get(int index) {
		validateIndex(index);

		return findByIndex(index).value;
	}

	public int size() {
		return size;
	}

	private void validateIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of range. Index: " + index + ", size: " + size);
		}
	}

	// index should in range, otherwise it will cause error.
	private Element findByIndex(int index) {
		Element e = head;
		for (int i = 0; i < index; i++) {
			e = e.next;
		}
		return e;
	}
}
