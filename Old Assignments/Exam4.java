public double avgGPA(ArrayList<Student> classList) {
  double sum = 0.00;
  double result;
  for (int i = 0; i < classList.size(); i++) {
    sum += classList.get(i).getGPA();
  }
  result = sum/((double)classList.size());
  return result;
}