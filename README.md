# KTerminal

KTerminal is a terminal display emulator written in [Kotlin](https://kotlinlang.org/) using [libGDX](http://libgdx.badlogicgames.com/) and [libKTX](https://libktx.github.io/). It was inspired by [AsciiTerminal](https://github.com/julianmaster/AsciiTerminal).

The goal of this project was to make an efficient, small way to emulate a terminal.

## Features

- Supports any rectangular font sheet made of glyphs of the same size, with no borders around the glyphs. Glyphs do not have to be square. In order for string and char writing to work properly a 256 Extended Ascii ([IBM Code Page 437](https://en.wikipedia.org/wiki/Code_page_437)) font sheet must be used in the formats shown in the example font sheets. Dwarf Fortress font sheets are compatible. Font sheets can contain any glyph and doesn't have to conform to the above mentioned standard, and can contain more than the 256 possible glyphs in said standard.
- Full color support for each glyph, including foreground color, background color, and transparency.
- Vertical and horizontal flipping, rotation, and scaling of each glyph
- Glyph offsets, meaning that glyphs are not confined to the center of their cell. This allows for smooth to move glyphs from cell to cell.
- Includes a default font sheet, located here in the sources in the assets folder if you want to use it to make your own.

## Example Font Sheets
Here are two font sheets. Glyphs must be in the order shown in order for string and char writing to appear properly, so if you tell it to write an 'a' char, an 'a' glyph appears on the terminal display.

![square layout](https://i.imgur.com/KkSCL0d.png)

![irregular layout](https://i.imgur.com/lSbFY2n.png)

## Example Project
Here's an [example project](https://github.com/heatherhaks/KTerminalColorPicker/) showing off the features of KTerminal.

![screenshot](https://i.imgur.com/p7YSawF.gif)

## Tutorial That Uses KTerminal

An awesome person decided to use KTerminal to do a roguelike tutorial, so I thought I'd link it: https://github.com/Larkenx/kotlin-roguelike-tutorial

## Usage

Using KTerminal should be easy.

### Adding to Gradle

```
//add the repository for jitpack
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}

//add the dependency
    //...
    dependencies {
        implementation 'com.github.heatherhaks:kterminal:-SNAPSHOT'
    }
    
//you can also specify a version, which will get you sources as well, just change the version string
//in this example, v1.0.4, to whatever the tag is for the release you want.
    //...
    dependencies {
        implementation 'com.github.heatherhaks:kterminal:v1.0.4'
    }
```

### Initialization

```
val kTerminalData = KTerminalData(
            width = 33, // width in characters
            height = 20, // height in characters
            defaultForegroundColor = Color.WHITE.toFloatBits(),
            defaultBackgroundColor = Color.BLACK.toFloatBits())

val kTerminalRenderer = KTerminalRenderer(
        batch = spriteBatch // the spritebatch to be used in rendering
        tilesetFile = "fontSheetName.png", //the name of the font sheet
        columns = 16, // number of horizontal glyphs on the font sheet
        rows = 16, // number of vertical glyphs on the font sheet
        scale = 1f) // Between 0 and 1 to make it smaller, higher than 1 to make it bigger

val kTerminalRenderer = KTerminalRenderer(spriteBatch) // uses the default font sheet and settings.
        
        
//glyphs can be set with a Char
val exampleGlyph(
        char = '@', // stored internally as the Int 64
        foreground = Color.YELLOW.toFloatBits(),
        background = Color.BLACK.toFloatBits(),
        rotation = 45f,
        scale = 0.5f,
        isFlippedX = true,
        isFlippedY = false)

//glyphs can be set with an Int
val exampleGlyph(
        value = 64, //64's char value is @
        foreground = Color.YELLOW.toFloatBits(),
        background = Color.BLACK.toFloatBits(),
        rotation = 45f,
        scale = 0.5f,
        isFlippedX = true,
        isFlippedY = false)
```

### Changing Terminal Font
```
kTerminalRenderer.set(tilesetFile = "otherFontSheet.png", scale = 1f)
```

### Resizing the Terminal
```
kTerminalData.resize(width = 80, height = 60)
```

### Changing Cursor Data

Cursor data can be set with brackets or direct function calls while using it from Kotlin code, or from direct functions only if using it from Java code. Brackets and direct function calls can be done back to back, such as:

```
kTerminalData[x, y][foreground, background]
kTerminalData.setCursorPosition(x, y).setCursorColor(foreground, background)
```
The following options can be set:

```
   //position
   kTerminalData[x, y]
   kTerminal.setCursorPosition(x, y)
   
   //position and offset
   kTerminalData[x, y, offsetX, offsetY]
   kTerminal.setCursorPosition(x, y)
   kTerminal.setCursorOffset(offsetX, offsetY)
   
   //color
   kTerminalData[foreground, background] // libGdx Color objects
   kTerminalData.setCursorColor(foreground, background) // libGdx Color objects' float bits
   
   //sub-cell resolution, requires specific sub-cell drawing glyphs, defaults to the locations shown in the included font sheet
   //in spaces 257-260 (numbering starts at 0)
   kTerminalData.setSubCell(topLeftColor,
                            topRightColor,
                            bottomLeftColor,
                            bottomRightColor,
                            topLeftValue, //defaults to value 257
                            topRightValue, // defaults to value 258
                            bottomLeftValue, // defaults to value 260
                            bottomRightValue) // defaults to value 259
    kTerminalData.setSubCell(subCellGlyph.copy()) // it can take a preconfigured SubCellGlyph object
                                                  // You might want to use a copy since clearing the
                                                  // terminal or the sub cell will change the object
                                                  // provided to this function
    kTerminalData.clearSubCell() // clears a sub-cell at the current cursor location
   
   //glyph rotation and scale
   kTerminalData[rotation, scale]
   kTerminalData.setCursorRotation(rotation)
   kTerminalData.setCursorScale(scale)
   
   //glyph offsets, numbers are in relation to the width/height of the glyphs. so an offsetX of -1.5f
   //would move a glyph one and a half cells to the left
   kTerminalData.setCursorOffset(offsetX, offsetY) //0f is no offset
   
   //glyph flipping
   kTerminalData[isFlippedX, isFlippedY]
   kTerminalData.setCursorFlip(isFlippedX, isFlippedY)
   
   //resetting the cursor
   kTerminalData.resetCursor()
```

### Writing

If one does not change the cursor data before writing, the previous or default settings will be used. One can change a setting and write in the same line.

```
//writing a char and changing cursor data at the same time
//this requires an IBM Code Sheet 437 format glyph sheet as shown in the example ones given at the top of this readme
kTerminalData.setCursorPosition(x, y).setCursorColor(foreground, background).write('@')

//writing a specific glyph using an integer
//glyph number is determined from the glyph sheet used in the renderer
//starting from 0 and going left to right, top to bottom
kTerminalData.write(28)

//writing a string
//this requires an IBM Code Sheet 437 format glyph sheet as shown in the example ones given at the top of this readme
//there is support for writing text in different orientations as well as several line wrapping options
    //writing direction options
    //KTerminalData.WRITE_LEFT_TO_RIGHT
    //KTerminalData.WRITE_RIGHT_TO_LEFT
    //KTerminalData.WRITE_TOP_TO_BOTTOM
    //KTerminalData.WRITE_BOTTOM_TO_TOP
    
    //wrapping options
    //KTerminalData.WRAP_NONE // no wrapping
    //KTerminalData.WRAP_NO_SHIFT // will wrap on same line
    //KTerminalData.WRAP_POSITIVE_SHIFT // x or y will increase on wrap depending on writing direction
    //KTerminalsData.WRAP_NEGATIVE_SHIFT //x or y will increase on wrap depending on writing direction
KTerminalData.write("Example")
kTerminalData.write("Example", direction = KTerminalData.WRITE_RIGHT_TO_LEFT, wrapping = KTerminalData.WRAP_POSITIVE_SHIFT)
```

### Shape Drawing

There are several shape drawing options, all of which can optionally be filled in and if filled in can optionally have fill colors. They can take chars or ints, and libGdx Color objects or AGBR floats.

```
kTerminalData.drawRect(
        width = 3
        height = 4,
        char = ' '
        isFilled = true,
        fillForeground = Color.WHITE,
        fillBackground = Color.BLACK)
        
//doesn't have to be a straight line
kTerminalData.drawLine(endX, endY, '#')

//a rect where you can specify different characters for each corner and horizontal and vertical sides
kTerminalData.drawBox(
        width = 5,
        height = 10,
        topLeft = '*',
        topRight = '*',
        bottomLeft = '*',
        bottomRight = '*',
        horizontal = '-',
        vertical = '|')
        
//a circle, cursor position is the center of the circle, radius starts at cursor position
kTerminalData.drawCircle(
        radius = 5,
        char = '#')

//a ellipse, draws similar to a rectangle
//the cursor position is the top left corner of the rect surrounding the ellipse
kTerminalData.drawEllipse(
        width = 10,
        height = 5,
        char = '#')
        
//a triangle, cursor is the top point, you must specify the bottom left point and the bottom right point
kTerminalData.drawTriangle(
        leftX = 10,
        leftY = 12
        rightX = 20,
        rightY = 12)

//boxes using single or double lines, requires an IBM code page 437 compatible font sheet
kTerminalData.drawSingleBox(
        width = 10,
        height = 5)

kTerminalData.drawDoubleBox(
        width = 20,
        height = 10)
```


### Clearing

Clearing uses the same syntax as writing, but ignores the cursor's color data and resets the values to the default values. If you want to clear to a specific color, use write instead.

```
//clear one character
kTerminalData[3, 4].clear()

//clear a rectangle
kTerminalData[4, 5].clear(width = 3, height = 4)

//clear the whole terminal, ignores all cursor data
kTerminalData.clearAll()
```

### Rendering

```
//this would go in your main rendering loop
batch.begin()

//x and y are the bottom left corner of the terminal image
//multiple terminals can be drawn with the same renderer
kTerminalRenderer.render(x, y, exampleKTerminalDataOne)
kTerminalRenderer.render(x, y, exampleKTerminalDataTwo)

batch.end()
```

### Disposing

```
kTerminalRenderer.dispose()
```
