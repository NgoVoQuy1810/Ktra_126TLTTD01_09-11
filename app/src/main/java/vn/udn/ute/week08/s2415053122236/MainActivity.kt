package vn.udn.ute.week08.s2415053122236

import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var edtTenHP: EditText
    private lateinit var edtQT: EditText
    private lateinit var edtCK: EditText
    private lateinit var btnKiemTra: Button
    private lateinit var btnXoa: Button
    private lateinit var tvKetQua: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtTenHP = findViewById(R.id.edtTenHP)
        edtQT = findViewById(R.id.edtQT)
        edtCK = findViewById(R.id.edtCK)
        btnKiemTra = findViewById(R.id.btnKiemTra)
        btnXoa = findViewById(R.id.btnXoa)
        tvKetQua = findViewById(R.id.tvKetQua)

        btnKiemTra.setOnClickListener {
            xuLyKiemTra()
        }

        btnXoa.setOnClickListener {
            xuLyXoa()
        }
    }

    private fun kiemTraTenHP(rawName: String): String? {
        val trimmed = rawName.trim()
        return if (trimmed.length in 1..40) trimmed else null
    }

    private fun kiemTraDiem(rawScore: String): Double? {
        val s = rawScore.trim()
        if (s.isEmpty() || s.contains(",")) return null
        val d = s.toDoubleOrNull() ?: return null
        if (!d.isFinite() || d < 0.0 || d > 10.0) return null
        return d
    }

    private fun tinhDiemT(qt: Double, ck: Double): Double {
        return 0.4 * qt + 0.6 * ck
    }

    private fun phanLoaiKetQua(t: Double, ck: Double): String {
        val d = 5.0
        val G = 9.0
        return if (t >= G && ck >= 3.0) "Tốt"
        else
            if (t >= d && ck >=3.0) "Đạt" else "Chưa đạt"
    }

    private fun xuLyKiemTra() {
        edtTenHP.error = null
        edtQT.error = null
        edtCK.error = null

        val tenHopLe = kiemTraTenHP(edtTenHP.text.toString())
        val qtHopLe = kiemTraDiem(edtQT.text.toString())
        val ckHopLe = kiemTraDiem(edtCK.text.toString())

        var coLoi = false

        if (tenHopLe == null) {
            edtTenHP.error = "Tên học phần sau trim phải từ 1..40 ký tự"
            coLoi = true
        }
        if (qtHopLe == null) {
            edtQT.error == "Điểm QT phải là số từ 0..10 (dùng dấu chấm)"
            coLoi = true
        }
        if (ckHopLe == null) {
            edtCK.error = "Điểm CK phải là số từ 0..10 (dùng dấu chấm)"
            coLoi = true
        }

        if (coLoi) {
            tvKetQua.text = ""
            return
        }

        val t = tinhDiemT(qtHopLe!!, ckHopLe!!)
        val ketLuan = phanLoaiKetQua(t, ckHopLe)

        tvKetQua.text = String.format(
            Locale.US,
            "Học phần: %s\nĐiểm QT:%.2f\nĐiểm T: %.2f\nKết luận: %s",
            tenHopLe, qtHopLe, ckHopLe, t, ketLuan
        )
    }
    private fun xuLyXoa() {
    }
}