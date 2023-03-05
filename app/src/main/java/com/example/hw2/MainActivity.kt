package com.example.hw2

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import java.util.concurrent.Executors
import java.util.stream.Collectors

class MainActivity : AppCompatActivity() {
    private lateinit var db: DB
    private lateinit var grade: Grade
    private lateinit var gradeDao: GradeDAO
    private lateinit var grades: List<Grade>
    private lateinit var addButton: Button
    private lateinit var calculateButton: Button
    private lateinit var finalGrade: TextView
    private lateinit var calculator: HomeworkCalculator
    private lateinit var recyclerView: RecyclerView
    private lateinit var hwList: ArrayList<String>
    private lateinit var hwAdapter: HomeworkAdapter
    private lateinit var hwInput: EditText
    private lateinit var resetButton: Button
    private lateinit var validation: Validation
    private lateinit var m1Input: EditText
    private lateinit var m2Input: EditText
    private lateinit var partInput: EditText
    private lateinit var presentInput: EditText
    private lateinit var projectInput: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(applicationContext, DB::class.java, "grades_db").build()
        gradeDao = db.gradeDao()

        Executors.newSingleThreadExecutor().execute {
            grades = gradeDao.getAll()
        }
        setContentView(R.layout.activity_main)
        hwList = ArrayList()
        validation = Validation()
        resetButton = findViewById(R.id.resetButton)
        addButton = findViewById(R.id.addButton)
        calculateButton = findViewById(R.id.calculateButton)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

        hwInput = findViewById(R.id.hwInput)
        m1Input = findViewById(R.id.m1Input)
        m2Input = findViewById(R.id.m2Input)
        partInput = findViewById(R.id.participationInput)
        presentInput = findViewById(R.id.presentationInput)
        projectInput = findViewById(R.id.projectInput)

        finalGrade = findViewById(R.id.finalGrade)


        if (!grades.isEmpty()) {
            grade = grades.get(0)
            if(grade.hw1.length > 0 ) hwList.add(grade.hw1)
            if(grade.hw2.length > 0 )  hwList.add(grade.hw2)
            if(grade.hw3.length > 0 )hwList.add(grade.hw3)
            if(grade.hw4.length > 0 )  hwList.add(grade.hw4)
            if(grade.hw5.length > 0 )hwList.add(grade.hw5)


            if(grade.m1.length > 0) m1Input.setText(grade.m1.toString())
            if(grade.m2.length > 0) m2Input.setText(grade.m2.toString())
            if(grade.part.length > 0)partInput.setText(grade.part.toString())
            if(grade.present.length > 0)presentInput.setText(grade.present.toString())
            if(grade.project.length > 0)projectInput.setText(grade.project.toString())
            if(grade.final.length > 0)finalGrade.setText(grade.final.toString())
        }
        hwAdapter = HomeworkAdapter(this, hwList)
        recyclerView.adapter = hwAdapter
        if (grades.isEmpty()) {
            grade = Grade()
            Executors.newSingleThreadExecutor().execute {
                gradeDao.insert(grade)
            }
        }


        m1Input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               
            }

            override fun afterTextChanged(p0: Editable?) {

                var m1 = m1Input.text.toString()
                    grade.m1 = m1
                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }
                }


        })

        m2Input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                var m2 = m1Input.text.toString()
                    grade.m2 = m2
                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }

            }

        })
        partInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                var part = partInput.text.toString()
                    grade.part = part
                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }

            }

        })
        presentInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                var present = presentInput.text.toString()
                    grade.present = present
                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }

            }

        })

        projectInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                var project = projectInput.text.toString()
                    grade.project = project
                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }

            }

        })


        //stex validiacia karvi u tiv ksarqenq bayc so far string
        addButton.setOnClickListener() {

            validation.put100(hwInput)
            var hwGrade = hwInput.text.toString()

            var hwDouble = hwGrade.toDouble();
            if (validation.inBounds(hwDouble)) {
                if (hwAdapter.itemCount < 4) {

                    var temp = "Homework" + (hwAdapter.itemCount + 1) + " = " + hwGrade
                    hwList.add(temp)
                    if(hwAdapter.itemCount == 1 ) grade.hw1 = temp
                    else if(hwAdapter.itemCount == 2 ) grade.hw2 = temp
                    else if(hwAdapter.itemCount == 3 ) grade.hw3 = temp
                    else if(hwAdapter.itemCount == 4 ) grade.hw4 = temp
                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }

                    hwAdapter.notifyDataSetChanged()
                } else if (hwAdapter.itemCount == 5) {
                    var temp = "Homework" + (hwAdapter.itemCount + 1) + " = " + hwGrade
                    hwList.add(temp)
                    grade.hw5 = temp
                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }
                    addButton.isEnabled = false
                    hwAdapter.notifyDataSetChanged()
                } else {

                    addButton.isEnabled = false
                    hwAdapter.notifyDataSetChanged()
                }
            } else {
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
            }

        }
        resetButton.setOnClickListener() {
            hwList.clear()

            hwAdapter.notifyDataSetChanged()
            grade.hw1 = ""
            grade.hw2 = ""
            grade.hw3 = ""
            grade.hw4 = ""
            grade.hw5 = ""



            Executors.newSingleThreadExecutor().execute {
                gradeDao.update(grade)
            }

            addButton.isEnabled = true
        }

        calculateButton.setOnClickListener() {
            validation.put100(m1Input)
            validation.put100(m2Input)
            validation.put100(partInput)
            validation.put100(presentInput)
            validation.put100(projectInput)

            var m1 = m1Input.text.toString().toDouble()
            var m2 = m2Input.text.toString().toDouble()
            var part = partInput.text.toString().toDouble()
            var present = presentInput.text.toString().toDouble()
            var project = projectInput.text.toString().toDouble()

            if (validation.inBounds(m1) && validation.inBounds(m2) &&
                validation.inBounds(part) && validation.inBounds(present) &&
                validation.inBounds(project)
            ) {
                val hw = ArrayList<Double>()
                if (hwList.size < 5) {
                    while (hwList.size < 5) {
                        hwList.add("Homework" + (hwAdapter.itemCount + 1) + " = " + 100)
                        hwAdapter.notifyDataSetChanged()
                    }
                }

                for (str in hwList) {
                    hw.add(str.substring(str.indexOf("=") + 2).trim().toDouble())
                }


                calculator = HomeworkCalculator(hw, m1, m2, part, present, project)
                val result = calculator.calculateGrade()
                finalGrade.setText(result.toString())


                grade.hw1 = hwList.get(0)
                grade.hw2 = hwList.get(1)
                grade.hw3 = hwList.get(2)
                grade.hw4 = hwList.get(3)
                grade.hw5 = hwList.get(4)
                grade.final = result.toString()



                    Executors.newSingleThreadExecutor().execute {
                        gradeDao.update(grade)
                    }


            } else Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show()
        }


    }


}