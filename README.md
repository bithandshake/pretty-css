
# pretty-css

### Overview

The <strong>pretty-css</strong> is a data attribute based atomic CSS preset and
theme kit for applications.

### deps.edn

```
{:deps {bithandshake/pretty-css {:git/url "https://github.com/bithandshake/pretty-css"
                                 :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/pretty-css/tree/release).

### Changelog

You can track the changes of the <strong>pretty-css</strong> library [here](CHANGES.md).

### Index

- [How to use Material Icons or Material Symbols?](#how-to-use-material-icons-or-material-symbols)

# Usage

### How to use the pretty-css?

Place the `pretty-css.min.css` file in your page that you can find in the
`resources/public` folder of this repository:

`resources/public/pretty-css.min.css`

To use the CSS profiles and presets of this library place its data attributes
on your elements:

```
[:div {:data-font-size :xs :data-color :muted}]
```

### How to use the scalable font profiles and presets?

Before using font profiles and presets such as `:data-font-size`, `:data-font-weight`, `:data-letter-spacing`
and `:data-line-height` set the font-size CSS property on your HTML element as
the REM value of your page that the `pretty-css` can use to make scalable font profiles.

To set the REM place something like this in one of your CSS files, it sets the REM to 10px and
by modifying this value you can scale the font sizes and other related values
in your page by one step (by modifying this value):

```
html { font-size: 10px }
```

Now, you can use the following profiles with the `:data-font-size` preset:

`:micro`, `:xxs`, `:xs`, `:s`, `:m`, `:l`, `:xl`, `:xxl`, `:3xl`, `:4xl`, `:5xl`

This profiles scales when you change the REM value of your page.

```
[:div {:data-font-size :m}]
```

The `:data-font-weight` preset could take the following profiles:

`:extra-light`, `:light`, `:normal`, `:medium`, `:bold`, `:extra-bold`

```
[:div {:data-font-weight :bold}]
```

The `:data-letter-spacing` preset with its default profiles and a special one (`:auto`):

`:micro`, `:xxs`, `:xs`, `:s`, `:m`, `:l`, `:xl`, `:xxl`, `:3xl`, `:4xl`, `:5xl`, `:auto`

Basically the best letter-spacing and font-size pairs are the same profiles:

```
[:div {:data-font-size :l :data-letter-spacing :l}]
```

By using the `:auto` value as letter spacing, the text will get the same profile
of letter spacing as font size.

```
[:div {:data-font-size :l :data-letter-spacing :auto}]
```

The `:data-line-height` preset has the same profiles as the `:data-letter-spacing`,
the `:auto` value and the `:text-block` value that makes the line height standardized
with other pretty-css layout profiles.

`:micro`, `:xxs`, `:xs`, `:s`, `:m`, `:l`, `:xl`, `:xxl`, `:3xl`, `:4xl`, `:5xl`, `:auto`

```
[:div {:data-font-size :l :data-line-height :l}]
```

```
[:div {:data-font-size :l :data-line-height :auto}]
```

By using the `:text-block` value as line height, the text gets a standardized
line height depends on the selected font size:

- Text block line height + micro font size = 18px line height

- Text block line height + XXS font size = 24px line height

- Text block line height + XS font size = 24px line height

- Text block line height + S font size = 24px line height

- Text block line height + M font size = 24px line height

- Text block line height + L font size = 36px line height

- Text block line height + XL font size = 36px line height

- Text block line height + XXL font size = 48px line height

- Text block line height + 3XL font size = 48px line height

- Text block line height + 4XL font size = 48px line height

- Text block line height + 5XL font size = 48px line height

### How to use the color palettes?

### How to use the color themes?







### How to use Material Icons or Material Symbols?

At first, place the pretty-css icon set files in your page head.
You can find them in the `resources/public` folder of this repository:

`resources/public/material-icons.css`

`resources/public/material-symbols.css`

Now place the icon font links in your page (or choose self hosting):

```
; For using Material Icons Filled:
[:link {:ref  "stylesheet"
        :type "text/css"
        :href "https://fonts.googleapis.com/icon?family=Material+Icons"}]

; For using Material Icons Outlined:        
[:link {:ref  "stylesheet"
        :type "text/css"
        :href "https://fonts.googleapis.com/icon?family=Material+Icons+Outlined"}]

; For using Material Symbols Filled or Outlined
[:link {:ref  "stylesheet"
        :type "text/css"
        :href "https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200"}]

```

After you provided the presence of the wanted icon set font files, you can
display icons by using the `:data-icon-family` attribute and adding the icon
name as the content of the element:

> Use dashes instead of using hyphens in icon names!

```
; This element displays an icon with a person who stands near by a tree.
; Using filled Material Icons set:

[:i {:data-icon-family :material-icons-filled} :nature_people]
```

```
; The same icon in outlined style:

[:i {:data-icon-family :material-icons-outlined} :nature_people]
```

```
; Or you can use Material Symbols as well:

[:i {:data-icon-family :material-symbols-filled}   :nature_people]
[:i {:data-icon-family :material-symbols-outlined} :nature_people]
```

To set the size of the displayed icon, use the `:data-icon-size` attribute
with one of the following values:

`:xxs`, `:xs`, `:s`, `:m`, `:l`, `:xl`, `:xxl`, `:3xl`, `:4xl`, `:5xl`

```
[:i {:data-icon-family :material-symbols-outlined
     :data-icon-size   :m} :nature_people]
```
