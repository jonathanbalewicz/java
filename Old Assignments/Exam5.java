public static double calculate(String question) {
  double result = 0.00;
  String[] questionArray = new String[3];
  questionArray = question.split(" ");
  if (questionArray[1].equals("+")) {
    result = (Double.parseDouble(questionArray[0])) + (Double.parseDouble(questionArray[2]));
  } else if (questionArray[1].equals("*")) {
    result = (Double.parseDouble(questionArray[0])) * (Double.parseDouble(questionArray[2]));
  } else {
    System.out.println("Error message: invalid operator");
  }
}