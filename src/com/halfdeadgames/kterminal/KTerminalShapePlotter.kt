package com.halfdeadgames.kterminal

import kotlin.math.abs

class KTerminalShapePlotter {
    companion object {
        fun plotRect(x: Int, y: Int, width: Int, height: Int) : List<Pair<Int, Int>> {
            val output: MutableList<Pair<Int, Int>> = mutableListOf()

            for(i in y until y + height) {
                for(j in x until x + width) {
                    if(i == y || i == y + height - 1|| j == x || j == x + width - 1){
                        output.add(Pair(j, i))
                    }
                }
            }

            return output
        }

        fun plotLine(startX: Int, startY: Int, endX: Int, endY: Int) :  List<Pair<Int, Int>> {
            val output: MutableList<Pair<Int, Int>> = mutableListOf(Pair(startX, startY), Pair(endX, endY))

            fun plotLineLow(x0: Int, y0: Int, x1: Int, y1: Int){
                val dx = x1 - x0
                var dy = y1 - y0
                var yi = 1

                if(dy < 0) {
                    yi = -1
                    dy = -dy
                }
                var d = 2*dy - dx
                var y = y0

                for(x in x0 until x1) {
                    output.add(Pair(x, y))
                    if(d > 0) {
                        y += yi
                        d -= 2*dx
                    }
                    d += 2*dy
                }
            }

            fun plotLineHigh(x0: Int, y0: Int, x1: Int, y1: Int) {
                var dx = x1 - x0
                val dy = y1 - y0
                var xi = 1

                if(dx < 0) {
                    xi = -1
                    dx = -dx
                }
                var d = 2*dx - dy
                var x = x0

                for(y in y0 until y1) {
                    output.add(Pair(x, y))
                    if(d > 0) {
                        x += xi
                        d -= 2*dy
                    }
                    d += 2*dx
                }
            }

            if(Math.abs(endY - startY) < Math.abs(endX - startX)) {
                if (startX > endX) {
                    plotLineLow(endX, endY, startX, startY)
                } else {
                    plotLineLow(startX, startY, endX, endY)
                }
            } else {
                if(startY > endY) {
                    plotLineHigh(endX, endY, startX, startY)
                } else {
                    plotLineHigh(startX, startY, endX, endY)
                }
            }

            return output.toList()
        }

        fun plotEllipse(startX: Int, startY: Int, endX: Int, endY: Int) :  List<Pair<Int, Int>> {
            val output: MutableList<Pair<Int, Int>> = mutableListOf()

            var x0 = startX - 1
            var y0 = startY - 1
            var x1 = endX - 2
            var y1 = endY - 2
            var a = abs(x1 - x0)
            val b = abs(y1 - y0)
            var b1 = b and 1 /* values of diameter */
            var dx = (4 * (1 - a) * b * b).toLong()
            var dy = (4 * (b1 + 1) * a * a).toLong() /* error increment */
            var err = dx + dy + (b1 * a * a).toLong()
            var e2: Long /* error of 1.step */

            if (x0 > x1) {
                x0 = x1
                x1 += a
            } /* if called with swapped points */
            if (y0 > y1) y0 = y1 /* .. exchange them */
            y0 += (b + 1) / 2
            y1 = y0 - b1   /* starting pixel */
            a *= 8 * a
            b1 = 8 * b * b

            do {
                output.add(Pair(x1, y0)) /*   I. Quadrant */
                output.add(Pair(x0, y0)) /*  II. Quadrant */
                output.add(Pair(x0, y1)) /* III. Quadrant */
                output.add(Pair(x1, y1)) /*  IV. Quadrant */
                e2 = 2 * err
                if (e2 <= dy) {
                    y0++
                    y1--
                    dy += a.toLong()
                    err += dy
                }  /* y step */
                if (e2 >= dx || 2 * err > dy) {
                    x0++
                    x1--
                    dx += b1.toLong()
                    err += dx
                } /* x step */
            } while (x0 <= x1)

            while (y0 - y1 < b) {  /* too early stop of flat ellipses a=1 */
                output.add(Pair(x0 - 1, y0)) /* -> finish tip of ellipse */
                output.add(Pair(x1 + 1, y0++))
                output.add(Pair(x0 - 1, y1))
                output.add(Pair(x1 + 1, y1--))
            }

            return output
        }

        fun plotCircle(centerX: Int, centerY: Int, radius: Int) : List<Pair<Int, Int>> {
            val output: MutableList<Pair<Int, Int>> = mutableListOf()

            var r = radius - 1
            var x = -r
            var y = 0
            var err = 2 - (2 * r)

            do {
                output.add(Pair(centerX - x - 1, centerY + y - 1))
                output.add(Pair(centerX - y - 1, centerY - x - 1))
                output.add(Pair(centerX + x - 1, centerY - y - 1))
                output.add(Pair(centerX + y - 1, centerY + x - 1))
                r = err
                if(r <= y) err += ++y * 2 + 1
                if(r > x || err > y) err += ++x * 2 + 1
            } while(x < 0)

            return output.toList()
        }

        fun plotTriangle(topX: Int, topY: Int, leftX: Int, leftY: Int, rightX: Int, rightY: Int) : List<Pair<Int, Int>> {
            val output: MutableList<Pair<Int, Int>> = mutableListOf()
            output.addAll(plotLine(topX, topY, leftX, leftY))
            output.addAll(plotLine(leftX, leftY, rightX, rightY))
            output.addAll(plotLine(rightX, rightY, topX, topY))
            return output.distinct()
        }
    }
}
