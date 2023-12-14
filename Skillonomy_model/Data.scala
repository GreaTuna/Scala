class Data(
    private var _coursesData: CoursesList,
    private var _studentsData: StudentsList,
    private var _teachersData: TeachersList) {
    
    def this() = this(new CoursesList, new StudentsList, new TeachersList)

    // Гетеры сетеры
    def coursesData: CoursesList = _coursesData
    def coursesData_=(newCoursesData: CoursesList): Unit = _coursesData = newCoursesData

    def studentsData: StudentsList = _studentsData
    def studentsData_=(newStudentsData: StudentsList): Unit = _studentsData = newStudentsData

    def teachersData: TeachersList = _teachersData
    def teachersData_=(newTeachersData: TeachersList): Unit = _teachersData = newTeachersData
    
    
    // Методы класса
    def addCourse(course: Course): Unit = {
        _coursesData.addCourseToList(course)
    }

    def addStudent(student: Student): Unit = {
        _studentsData.addStudentToList(student)
    }

    def addTeacher(teacher: Teacher): Unit = {
        _teachersData.addTeacherToList(teacher)
    }

    def printCoursesData(): Unit = {

        _coursesData.coursesList.foreach(course => {
            course.printCourseInfo()
        })
    }

    def printStudentsData(): Unit = {

        _studentsData.studentsList.keys.foreach(key => {
            _studentsData.studentsList(key).printUserInfo()
        })
    }

    def printTeachersData(): Unit = {

        _teachersData.teachersList.keys.foreach(key => {
            _teachersData.teachersList(key).printUserInfo()
        })
    }

    def printTeachersСourseLogs(): Unit = {

        _teachersData.teachersList.keys.foreach(key => {
            _teachersData.teachersList(key).courseLog(this)
        })
    }
}