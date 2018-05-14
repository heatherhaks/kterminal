# KTerminal

KTerminal is a terminal emulator written in [Kotlin](https://kotlinlang.org/) using [libGDX](http://libgdx.badlogicgames.com/) and [libKTX](https://libktx.github.io/). It was inspired by [AsciiTerminal](https://github.com/julianmaster/AsciiTerminal).

The goal of this project was to make an efficient, small way to emulate a terminal.

## Features

- Supports 256 Extended Ascii ([IBM Code Page 437](https://en.wikipedia.org/wiki/Code_page_437)) font sheets, in a 16x16 square layout with no buffers around the glyphs. Dwarf Fortress font sheets are compatible if they're square.
- Full color support for each glyph, including foreground color, background color, and transparency.

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
        foregroundColor = Color.YELLOW,
        backgroundColor = Color.BLACK)
```

### Writing

KTerminal has an internal cursor that stores the starting position for writing as well as the colors. You can input position and color information in brackets like in the following examples, but doing so is optional for both position and color. There are also several functions for shape drawing.

```
//setting cursor info
kTerminal[x, y][Color.GREEN, Color(0f, 0f, 1f, 0.5f)]
//using the current settings of the internal cursor:
kTerminal.write("Example string.")

//setting the position/color while writing
kTerminal[x, y].write(exampleGlyph)
kTerminal[x, y][foregroundColor, backgroundColor].write('#')

//shape drawing
kTerminal[x, y][foregroundColor, backgroundColor].drawRect(
        width = 3
        height = 4,
        char = ' '
        isFilled = true) // whether it's filled or just the outline
        
//doesn't have to be a straight line
kTerminal[x, y].drawLine(endX, endY, '#')

//a hollow rect where you can specify different characters for each corner and horizontal and vertical sides
kTerminal.drawBox(
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
kTerminal[3, 4].clear()

//clear a rectangle
kTerminal[4, 5].clear(width = 3, height = 4)

//clear the whole terminal, ignores all cursor data
kTerminal.clearAll()
```

### Displaying the terminal texture

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
kTerminal.dispose()
```
