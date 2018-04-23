# KTerminal

KTerminal is a terminal emulator written in [Kotlin](https://kotlinlang.org/) using [libGDX](http://libgdx.badlogicgames.com/) and [libKTX](https://libktx.github.io/). It was inspired by [AsciiTerminal](https://github.com/julianmaster/AsciiTerminal).

The goal of this project was to make an efficient, small way to emulate a terminal. Some terminal emulators I've seen have extra functions, like the ability to write a string to the terminal. I decided that such logic should exist outside of the basic emulator, which is why this project seems so bare bones.

## Features

- Supports 256 Extended Ascii (IBM Code Page 437) font sheets, in a 16x16 square layout with no buffers around the glyphs. Dwarf Fortress font sheets are compatible if they're square.
- Full color support for each glyph, including foreground color, background color, and transparency.
- Outputs a single texture for ease of use with however you display graphics.
- Only updates the texture when the terminal is written to.

## Example Project
Here's an [example project](https://github.com/heatherhaks/KTerminalExample/) showing off the features of KTerminal.

![screenshot](https://i.imgur.com/CQiJlpN.gifv)

## Usage

Using KTerminal should be easy.

### Initialization

```
var kTerminal: KTerminal = KTerminal(
                width = 33, // width in characters
                height = 20, // height in characters
                tilesetFile = "fontSheetName.png", // the name of the font sheet being used
                scale = 1f, // Between 0 and 1 to make it smaller, higher than 1 to make it bigger.
                defaultForegroundColor = Color.WHITE,
                defaultBackgroundColor = Color.BLACK,
                inputBatch = batch)
```

### Writing

```
kTerminal.write(2,  // x position
                3,  // y position
                '@',    // character to be written
                Color.YELLOW, // foreground color - optional
                Color.BLACK) // background color - optional

// this is the same as the above, but allows you to reuse cells, like for standard items
var dataCell = KTerminalDataCell('@', Color.WHITE, Color.BLACK)
kTerminal.write(x, y, dataCell)
```

### Clearing

```
kterminal.clear() //clears entire terminal
kterminal.clear(4, 8) // clears a specific cell

//the following would clear a rectangular area
kterminal.clear(2, // starting x
                5, // starting y
                4, // width
                3) // height
```

### Displaying the terminal texture

```
kTerminal.update()

batch.begin()
batch.draw(kTerminal.texture, // the texture
           0,  // x position
           100) // y position
batch.end()
```

### Disposing

```
kTerminal.dispose()
```
