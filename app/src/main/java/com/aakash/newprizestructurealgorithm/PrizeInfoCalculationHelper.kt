package com.aakash.newprizestructurealgorithm

import android.util.Log

class PrizeInfoCalculationHelper() {

    fun calculatePrizeInfo(oldArray: ArrayList<PrizeInfoModel>,
                                   oldSize: Int, newSize: Int): HashMap<Int, Double> {

        val oldMap = HashMap<Int, Double>()
        oldArray.forEach {
            oldMap[it.key] = it.value
        }
        val newMap = HashMap<Int, Double>()
        val oldWinnerSize = oldMap.size
        val sizeFactor: Double = newSize.toDouble() / oldSize.toDouble()
        val newWinnerSize = oldWinnerSize * sizeFactor
        Log.w("new Winner size : ", newWinnerSize.toString())
        var totalPrize = 0.0

        if (sizeFactor >= 1) {
            for (i in 1 .. newWinnerSize.toInt()) {
                val mapId :Double = if (i % sizeFactor.toInt() == 0) i/ sizeFactor else i/sizeFactor + 1
                val reward = oldMap[mapId.toInt()] ?:0 / sizeFactor
                if (totalPrize + reward <= 1) {
                    totalPrize += reward
                    newMap.put(i, reward)
                }
            }
        } else {
            for (i in 1 .. newWinnerSize.toInt()) {
                var j = i
                var reward = 0.0
                while (j <= oldWinnerSize) {
                    reward += oldMap[j] ?: 0.0
                    j += newWinnerSize.toInt()
                }
                newMap[i] = reward
            }
        }
        return newMap
    }
}