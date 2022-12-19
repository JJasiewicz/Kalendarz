import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private var startDate: Calendar? = null
    private var endDate: Calendar? = null
    private lateinit var calendarView: CalendarView
    private lateinit var startButton: Button
    private lateinit var endButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize calendar view
        calendarView = findViewById(R.id.calendarView)
        calendarView.setOnDayClickListener { eventDay ->
            val clickedDayCalendar = eventDay.calendar
            // Handle click event on calendar day
            if (startDate == null) {
                startDate = clickedDayCalendar
                startButton.isEnabled = true
            } else if (endDate == null) {
                endDate = clickedDayCalendar
                endButton.isEnabled = true
            } else {
                startDate = clickedDayCalendar
                endDate = null
                endButton.isEnabled = false
            }
            updateCalendar()
        }

        // Initialize start and end buttons
        startButton = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            // Handle start button click event
            startDate = null
            startButton.isEnabled = false
            updateCalendar()
        }
        endButton = findViewById(R.id.endButton)
        endButton.setOnClickListener {
            // Handle end button click event
            endDate = null
            endButton.isEnabled = false
            updateCalendar()
        }
    }

    private fun updateCalendar() {
        calendarView.removeAllEvents()
        if (startDate != null) {
            calendarView.addEvent(EventDay(startDate!!, R.drawable.ic_start))
        }
        if (endDate != null) {
            calendarView.addEvent(EventDay(endDate!!, R.drawable.ic_end))
        }
    }
}
