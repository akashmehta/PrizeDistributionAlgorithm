package com.aakash.newprizestructurealgorithm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<PrizeInfoModel>()
    private val itemList1 = ArrayList<PrizeInfoModel>()
    private val entryFee: Int = 10
    private val margin: Double = 0.1

    init {
        repeat (3) {
            itemList.add(PrizeInfoModel(it + 1, 0.1))
        }
        repeat(6) {
            itemList.add(PrizeInfoModel(it + 4, 0.05))
        }
        repeat(5) {
            itemList.add(PrizeInfoModel(it + 10, 0.03))
        }
        repeat(8) {
            itemList.add(PrizeInfoModel(it + 15, 0.02))
        }
        repeat(9) {
            itemList.add(PrizeInfoModel(it + 23, 0.01))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvContent.layoutManager = LinearLayoutManager(this)
        rvContent.adapter = PrizeInfoAdapter(itemList)
        button.setOnClickListener {

            val helper = PrizeInfoCalculationHelper()
            val newItemsMap = helper.calculatePrizeInfo(itemList, 100, tvNewSize.text.toString().toInt())

            itemList.clear()
            val iterator = newItemsMap.iterator()
            val sampleList = ArrayList<PrizeInfoModel>()
            while (iterator.hasNext()) {
                val item = iterator.next()
                sampleList.add(PrizeInfoModel(item.key, item.value))
            }
            itemList.addAll(sampleList.sortedBy { it.key })
            rvContent.adapter?.notifyDataSetChanged()


            val totalPrizeMoney = entryFee * (itemList.size) * (1 - margin)
            itemList.forEach {
                itemList1.add(PrizeInfoModel(it.key, it.value * totalPrizeMoney))
            }
            rvContent1.layoutManager = LinearLayoutManager(this)
            rvContent1.adapter = PrizeInfoAdapter(itemList1)

        }
    }
}
