
class Book {
 private String title;
 private int pages;
 private int rating;
 public static int bookCount

 public Book(String title, int pages, int rating) {
  this.title = title;
  this.pages = pages;
  this.rating = rating;
 }
 public void changeRating(int newRating) {
   if (newRating <= 10 && newRating >=0) {
     this.rating = newRating;
   }
   
 }
 public int bookCount() {
   return bookCount;
 }
 public String toString() {
  return bookCount;
 }
}

