public class A5Exercises {

	// PART 1

	/*
	 * Purpose: get a count of the number of elements in the array
	 * Parameters: int[] arr
	 * Returns: int - the number of elements
	 * Post-condition - the array contents are unchanged
	 */
	public static int countMultiples(int[] arr, int x) {
		if (arr.length == 0) {
			return 0;
		} else {
			return countMultiplesRec(arr, x, arr.length-1);
		}
	}

	public static int countMultiplesRec(int[] arr, int x, int i) {
		if (i < 0) {
			return 0;
		} else if (arr[i] % x == 0) {
			return 1 + countMultiplesRec(arr, x, i-1);
		} else {
			return 0 + countMultiplesRec(arr, x, i-1);
		}
	}
		
	/*
	 * Purpose: double all values in the given array
	 * Parameters: int[] array - the array to modify
	 * Returns: void - nothing
	 */
	public static void doubleAll(int[] array) {
		if (array.length == 0) {
			return;
		} else {
			doubleAllRec(array, array.length-1);
		}
	}

	public static void doubleAllRec(int[] array, int i) {
		if (i < 0) {
			return;
		} else {
			array[i] *= 2;
			doubleAllRec(array, i-1);
		}
	}
	
	/*
	 * Purpose: get the minimum value found in the array
	 * Parameters: int[] array - the array to search
	 * Returns: int - minimum value found in the array
	 *                or -1 if the array is empty
	 * Post-condition - the array contents are unchanged
	 */
	public static int getMinimum(int[] array) {
		if (array.length == 0) {
			return -1;
		} else {
			return getMinimumRec(array, array.length-1, array[array.length-1]);
		}
	}
	
	public static int getMinimumRec(int[] array, int i, int min) {
		if (i < 0) {
			return min;
		} else {
			if (i != 0 && min > array[i-1]) {
				min = array[i-1];
			}
			min = getMinimumRec(array, i-1, min);
			return min;
		}
	}
	



	// PART II

	/*
	 * Purpose: get the total number of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of books
	 * Post-condition: s is not modified
	 */
	public static int totalBooks(Stack<Book> s) {
		int count = 0;
		if (s.isEmpty()) {
			return 0;
		} 
		Book top = s.pop();
		count = totalBooks(s);
		count++;
		s.push(top);
		return count;
	}


	/*
	 * Purpose: get the total number of pages of all 
	 *          books in the stack
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: int - the total number of pages
	 * Post-condition: s is not modified
	 */
	public static int totalPages(Stack<Book> s) {
		int sum = 0;
		if (s.isEmpty()) {
			return 0;
		}
		Book top = s.pop();
		sum = totalPages(s);
		sum += top.getPages();
		s.push(top);
		return sum;
	}
	
	/*
	 * Purpose: get the average number of pages of books in s
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: double - the average number of pages
	 *                   0.0 if there are no books in s
	 * Post-condition: s is not modified
	 */
	public static double averagePages(Stack<Book> s) {
		// You don't need to change this, if you have
		// completed the previous two exercises
		// correctly, it should pass all the tests
		if (s.isEmpty()) {
			return 0.0;
		} else {
			double sum = totalPages(s);
			int count = totalBooks(s);
			return sum/count;
		}
	}

	/*
	 * Purpose: determine whether toFind is contained in s
	 * Parameters: Stack<Book> s - the stack of books
	 *             Book toFind - the book to search for
	 * Returns: boolean - true if s contains toFind, false otherwise
	 * Post-condition: s is not modified
	 */
	public static boolean containsBook(Stack<Book> s, Book toFind) {
		boolean found = false;
		if (s.isEmpty()) {
			return found;
		}
		Book top = s.pop();
		if (top == toFind) {
			found = true;
		} else {
			found = containsBook(s, toFind);
		}

		s.push(top);
		return found;
	}

	/*
	 * Purpose: determine the books in s are stacked correctly
	 *          (ie. there is never a book stacked on top of 
	 *               another book with fewer pages)
	 * Parameters: Stack<Book> s - the stack of books
	 * Returns: boolean - true if books in s are stacked correctly
	 * Post-condition: s is not modified
	 */
	public static boolean stackedCorrectly(Stack<Book> s) {
		boolean stacked = true;
		if (s.isEmpty()) {
			return stacked;
		}
		if (totalBooks(s) >= 2) {
			Book top1 = s.pop();
			Book top2 = s.pop();
			if (top1 != null && top2 != null && top1.getPages() > top2.getPages()) {
				stacked = false;
			} else {
				stacked = stackedCorrectly(s);
			}
			s.push(top2);
			s.push(top1);
		}
		return stacked;
	}
}
