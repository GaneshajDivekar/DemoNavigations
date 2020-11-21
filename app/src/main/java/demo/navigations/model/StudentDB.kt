package demo.navigations.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "student_data")
open class StudentDB :Serializable {

    @PrimaryKey(autoGenerate = true)
    var studentId: Int = 0

    @ColumnInfo(name = "first_name")
    var first_name: String? = null

    @ColumnInfo(name = "second_name")
    var second_name: String? = null

    @ColumnInfo(name = "last_name")
    var last_name: String? = null

    @ColumnInfo(name = "address")
    var address: String? = null

    @ColumnInfo(name = "counter")
    var counter: Int = 0

}