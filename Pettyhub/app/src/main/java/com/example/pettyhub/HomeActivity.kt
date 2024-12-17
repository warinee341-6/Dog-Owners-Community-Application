package com.example.pettyhub


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.pettyhub.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: RecommendAdapter
    private lateinit var dataList:ArrayList<Petdata>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        binding.searchTxt.setOnClickListener {
            startActivity(Intent(this,SearchActivity::class.java))
        }

        binding.health.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","สุขภาพ")
            myIntent.putExtra("CATEGORY","สุขภาพ")
            startActivity(myIntent)
        }
        binding.care.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","การดูแล")
            myIntent.putExtra("CATEGORY","การดูแล")
            startActivity(myIntent) }
        binding.food.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","อาหาร")
            myIntent.putExtra("CATEGORY","อาหาร")
            startActivity(myIntent) }
        binding.lifeStyle.setOnClickListener {
            var myIntent=Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","ไลฟ์สไตล์")
            myIntent.putExtra("CATEGORY","ไลฟ์สไตล์")
            startActivity(myIntent) }

    }

    private fun setUpRecyclerView() {
        dataList= ArrayList()

        binding.rvRecommend.layoutManager=LinearLayoutManager(this)
        var db=Room.databaseBuilder(this@HomeActivity,AppDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("petdata.db")
            .build()

        var daoObject=db.getDao()
        var petdatas=daoObject.getAll()
        for (i in petdatas!!.indices){
            dataList.add(petdatas[i]!!)
            rvAdapter= RecommendAdapter(dataList,this)
            binding.rvRecommend.adapter=rvAdapter
        }
    }

}

