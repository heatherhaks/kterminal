# KTerminal

KTerminal is a terminal emulator written in [Kotlin](https://kotlinlang.org/) using [libGDX](http://libgdx.badlogicgames.com/) and [libKTX](https://libktx.github.io/). It was inspired by [AsciiTerminal](https://github.com/julianmaster/AsciiTerminal).

The goal of this project was to make an efficient, small way to emulate a terminal.

## Features

- Supports 256 Extended Ascii ([IBM Code Page 437](https://en.wikipedia.org/wiki/Code_page_437)) font sheets, in a 16x16 layout with no buffers around the glyphs. Dwarf Fortress font sheets are compatible. Font sheets do not have to be square.
- Full color support for each glyph, including foreground color, background color, and transparency.
- Vertical and horizontal flipping, rotation, and scaling of each glyph

## Example Project
Here's an [example project](https://github.com/heatherhaks/KTerminalColorPicker/) showing off the features of KTerminal.

![screenshot](https://i.imgur.com/p7YSawF.gif)

## Usage

Using KTerminal should be easy.

### Adding to Gradle

```
allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
        ....
            dependencies {
            compile 'com.github.heatherhaks:kterminal:-SNAPSHOT'
    }
```

### Initialization

```
val kTerminalData = KTerminalData(
        width = 33, // width in characters
        height = 20, // height in characters
        defaultForeground = Color.WHITE,
        defaultBackground = Color.BLACK)

val kTerminalRenderer = KTerminalRenderer(
        tilesetFile = "fontSheetName.png", //the name of the font sheet
        scale = 1f // Between 0 and 1 to make it smaller, higher than 1 to make it bigger
        batch = spriteBatch) // the spritebatch to be used in rendering
        
val exampleGlyph(
        char = '@',
        foreground = Color.YELLOW,
        background = Color.BLACK,
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

### Writing

KTerminal has an internal cursor that stores the starting position for writing as well as the colors. You can input position and color information in brackets like in the following examples, but doing so is optional for both position and color. There are also several functions for shape drawing.

```
//possible cursor settings, can be called in any order right after eachother
        //position
        kTerminalData[x, y]
        //color
        kTerminalData[foreground, background]
        //rotation/scaling
        kTerminalData[rotationInDegrees, scale]
        //flipping
        kTerminalData[isFlippedX, isFlippedY]
        
//setting cursor info
kTerminalData[x, y][Color.GREEN, Color(0f, 0f, 1f, 0.5f)]
//using the current settings of the internal cursor:
kTerminalData.write("Example string.")

//setting the position/color while writing
kTerminalData[x, y].write(exampleGlyph)
kTerminalData[x, y][foregroundColor, backgroundColor].write('#')

//shape drawing
kTerminalData[x, y][foregroundColor, backgroundColor].drawRect(
        width = 3
        height = 4,
        char = ' '
        isFilled = true) // whether it's filled or just the outline
        
//doesn't have to be a straight line
kTerminalData[x, y].drawLine(endX, endY, '#')

//a hollow rect where you can specify different characters for each corner and horizontal and vertical sides
kTerminalData.drawBox(
        width = 5,
        height = 10,
        topLeft = '*',
        topRight = '*',
        bottomLeft = '*',
        bottomRight = '*',
        horizontal = '-',
        vertical = '|')
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
