
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

### What's in this library?

...

### How to use Material Icons or Material Symbols?

At first, place the pretty-css icon set files in your page head.
You can find them in the `resources/public` folder of this repository:

`resources/public/material-icons.css`

`resources/public/material-symbols.css`

Now place the icon font links in your page head (or choose self hosting):

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

After you provided the presence of the selected icon set font files, you can
display icons by using the `:data-icon-family` attribute and adding the icon
name as the content of the element:

> Use dashes instead of using hyphens in icon names!

```
; This element displays an icon with a person who stands near by a tree
; by using the filled Material Icons set:

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
