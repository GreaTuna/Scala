object Main extends App {

  val arr1 = Array.empty[Int]
  val arr: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 9, 8, 2, 9, 22, 5)

  def print_array(arr: Array[Int]): Unit = {
    for (element <- arr) {
      print(s"$element ")
    } 
    println()
  }


  def filter(arr: Array[Int]): Unit = {
    for (element <- arr) {
      if(element % 2 != 0) {
        print(s"$element ")
      }
    } 
    println()
  }


  def find_min_max(arr: Array[Int]): Unit = {

    var min: Int = arr(0) 
    var max: Int = arr(0)

    for (element <- arr) {
      if (element < min) {
        min = element
      }
      if (element > max) {
        max = element
      }
    }

    println(s"min element: $min, max element: $max")
  }


  def find_indexes(arr: Array[Int], target: Int): Unit = {

    var found = false

    for(index <- 0 until arr.length) {

      if(arr(index) == target) {
        print(s"target($target) found, index in array: $index ")
        print("\n")
        found = true
      }
    }

    if(found == false) {
      print(s"this array dont include target($target)")
    }
    else {
      print("")
    }
  }


  def is_exist(arr: Array[Int], target: Int): Unit = {

    var found = false

    for(index <- 0 until arr.length) {

      if(arr(index) == target) {
        found = true
      }
    }

    if(found == false) {
      print(s"this element ($target) is not in array")
      print("\n")
    }
    else {
      print(s"this element($target) is in array")
      print("\n")
    }
  }


  def is_empty(arr: Array[Int]): Unit = {
    if(arr.isEmpty) {
      println("array is empty")
    }
    else {
      println("array is not empty")
    }
  }


  def find_head_tail(arr: Array[Int]): Unit = {
    val head = arr.head
    val tail = arr.tail

    println(s"array head: $head, array tail: ${tail.mkString(" ")}")
  }


  def average(arr: Array[Int]): Unit = {
    var sum: Int = 0
    var avg: Double = 0

    for (element <- arr) {
      sum += element
    }

    avg = sum.toDouble / arr.length
    println(s"average: $avg")
  }


  def product(arr: Array[Int]): Unit = {
    var prd = arr(0)

    for(index <- 1 until arr.length) {
      prd *= arr(index)
    }

    println(s"product: $prd")
  }
  
  is_empty(arr)
  filter(arr)
  print_array(arr)
  find_min_max(arr)
  is_exist(arr, 2)
  find_indexes(arr, 9)
  find_head_tail(arr)
  average(arr)
  product(arr)
}