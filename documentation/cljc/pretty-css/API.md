
# pretty-css.api isomorphic namespace

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > pretty-css.api

### Index

- [adaptive-border-radius](#adaptive-border-radius)

- [apply-color](#apply-color)

- [apply-dimension](#apply-dimension)

- [apply-preset](#apply-preset)

- [badge-attributes](#badge-attributes)

- [border-attributes](#border-attributes)

- [bubble-attributes](#bubble-attributes)

- [color-attributes](#color-attributes)

- [default-attributes](#default-attributes)

- [font-attributes](#font-attributes)

- [icon-attributes](#icon-attributes)

- [indent-attributes](#indent-attributes)

- [marker-attributes](#marker-attributes)

- [outdent-attributes](#outdent-attributes)

- [text-attributes](#text-attributes)

### adaptive-border-radius

```
@param (keyword) border-radius
@param (number) ratio
```

```
@usage
(adaptive-border-radius :s 0.3)
```

```
@example
(adaptive-border-radius :s 0.3)
=>
"calc(var(--border-radius-s) * 0.3)"
```

```
@example
(adaptive-border-radius nil 0.3)
=>
nil
```

```
@return (string)
```

<details>
<summary>Source code</summary>

```
(defn adaptive-border-radius
  [border-radius ratio]
  (if border-radius (str "calc(var(--border-radius-"(name border-radius)") * "ratio")")))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [adaptive-border-radius]]))

(pretty-css.api/adaptive-border-radius ...)
(adaptive-border-radius                ...)
```

</details>

---

### apply-color

```
@param (map) element-attributes
{:style (map)(opt)}
@param (keyword) color-key
@param (keyword) color-data-key
@param (keyword or string) color-value
```

```
@usage
(apply-color {:style {...}} :color :data-color :muted)
```

```
@example
(apply-color {...} :color :data-color :muted)
=>
{:data-color :muted}
```

```
@example
(apply-color {...} :color :data-color "#fff")
=>
{:data-color :var :style {"--color" "fff"}}
```

```
@example
(apply-color {:style {:padding "12px"}} :color :data-color "#fff")
=>
{:data-color :var :style {"--color" "fff" :padding "12px"}}
```

```
@return (map)
{:style (map)}
```

<details>
<summary>Source code</summary>

```
(defn apply-color
  [element-attributes color-key color-data-key color-value]
  (cond (keyword? color-value) (-> element-attributes (assoc-in [color-data-key] color-value))
        (string?  color-value) (-> element-attributes (assoc-in [:style (str "--" (name color-key))] color-value)
                                                      (assoc-in [color-data-key] :var))
        :return element-attributes))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [apply-color]]))

(pretty-css.api/apply-color ...)
(apply-color                ...)
```

</details>

---

### apply-dimension

```
@param (map) element-attributes
@param (keyword) dimension-key
@param (keyword) dimension-data-key
@param (keyword, px or string) dimension-value
```

```
@usage
(apply-dimension {:style {...} :width :data-block-width 42})
```

```
@example
(apply-dimension {...} :width :data-block-width 42)
=>
{:style {:width "42px"}}
```

```
@example
(apply-dimension {...} :width :data-block-width "42%")
=>
{:style {:width "42%"}}
```

```
@example
(apply-dimension {...} :width :data-block-width :s)
=>
{:data-block-width :s}
```

```
@return (map)
```

<details>
<summary>Source code</summary>

```
(defn apply-dimension
  [element-attributes dimension-key dimension-data-key dimension-value]
  (cond (keyword? dimension-value) (assoc    element-attributes dimension-data-key dimension-value)
        (integer? dimension-value) (assoc-in element-attributes [:style dimension-key] (str dimension-value "px"))
        (string?  dimension-value) (assoc-in element-attributes [:style dimension-key]      dimension-value)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [apply-dimension]]))

(pretty-css.api/apply-dimension ...)
(apply-dimension                ...)
```

</details>

---

### apply-preset

```
@param (map) presets
@param (map) element-props
{:preset (keyword)(opt)}
```

```
@usage
(apply-preset {:my-preset {...}}
              {:preset :my-preset ...})
```

```
@example
(apply-preset {:my-preset {:hover-color :highlight}}
              {:preset :my-preset})
=>
{:hover-color :highlight
 :preset      :my-preset}
```

```
@return (map)
```

<details>
<summary>Source code</summary>

```
(defn apply-preset
  [presets {:keys [preset] :as element-props}]
  (if preset (let [preset-props (get presets preset)]
                  (merge preset-props element-props))
             element-props))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [apply-preset]]))

(pretty-css.api/apply-preset ...)
(apply-preset                ...)
```

</details>

---

### badge-attributes

```
@param (map) element-attributes
@param (map) element-props
{:badge-color (keyword)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :badge-content (string)
 :badge-position (keyword)
  :tl, :tr, :br, :bl}
```

```
@usage
(badge-attributes {...} {...})
```

```
@example
(badge-attributes {...} {:badge-color :primary :badge-content "420" :badge-position :tr})
=>
{:data-badge-color    :primary
 :data-badge-content  "420"
 :data-badge-position :tr}
```

```
@return (map)
{:data-badge-color (keyword)
 :data-badge-content (string)
 :data-badge-position (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn badge-attributes
  [element-attributes {:keys [badge-color badge-content badge-position]}]
  (merge element-attributes {:data-badge-content  badge-content
                             :data-badge-color    badge-color
                             :data-badge-position badge-position}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [badge-attributes]]))

(pretty-css.api/badge-attributes ...)
(badge-attributes                ...)
```

</details>

---

### border-attributes

```
@param (map) element-attributes
@param (map) element-props
{:border-color (keyword or string)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :border-radius (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :border-position (keyword)(opt)
  :all, :bottom, :top, :left, :right, :horizontal, :vertical
 :border-width (keyword)(opt)}
```

```
@usage
(border-attributes {...} {...})
```

```
@example
(border-attributes {...} {:border-color :default :border-radius :s :border-width :s})
=>
{:data-border-color  :default
 :data-border-family :s
 :data-border-size   :s}
```

```
@return (map)
{:data-border-position (keyword)
 :data-border-radius (keyword)
 :data-border-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn border-attributes
  [element-attributes {:keys [border-color border-position border-radius border-width]}]
  (-> (merge element-attributes {:data-border-radius   border-radius
                                 :data-border-position border-position
                                 :data-border-width    border-width})
      (apply-color :border-color :data-border-color border-color)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [border-attributes]]))

(pretty-css.api/border-attributes ...)
(border-attributes                ...)
```

</details>

---

### bubble-attributes

```
@param (map) element-attributes
@param (map) element-props
{:bubble-color (keyword)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :bubble-content (string)(opt)
 :bubble-position (keyword)(opt)
  :left, :right}
```

```
@usage
(bubble-attributes {...} {...})
```

```
@example
(bubble-attributes {...} {:bubble-color :primary :bubble-content "Hello bubble!" :bubble-position :left})
=>
{:data-bubble-color    :primary
 :data-bubble-content  "Hello bubble!"
 :data-bubble-position :left}
```

```
@return (map)
{:data-bubble-color (keyword)
 :data-bubble-content (string)
 :data-bubble-position (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn bubble-attributes
  [element-attributes {:keys [bubble-color bubble-content bubble-position]}]
  (merge element-attributes {:data-bubble-color    bubble-color
                             :data-bubble-position bubble-content
                             :data-bubble-content  bubble-position}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [bubble-attributes]]))

(pretty-css.api/bubble-attributes ...)
(bubble-attributes                ...)
```

</details>

---

### color-attributes

```
@param (map) element-attributes
@param (map) element-props
{:color (keyword or string)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :fill-color (keyword or string)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :hover-color (keyword or string)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning}
```

```
@usage
(color-attributes {...} {...})
```

```
@example
(color-attributes {...} {:color :default :fill-color :highlight :hover-color :highlight})
=>
{:data-color       :default
 :data-fill-color  :highlight
 :data-hover-color :highlight}
```

```
@return (map)
{}
```

<details>
<summary>Source code</summary>

```
(defn color-attributes
  [element-attributes {:keys [color fill-color hover-color]}]
  (-> element-attributes (apply-color :color       :data-color       color)
                         (apply-color :fill-color  :data-fill-color  fill-color)
                         (apply-color :hover-color :data-hover-color hover-color)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [color-attributes]]))

(pretty-css.api/color-attributes ...)
(color-attributes                ...)
```

</details>

---

### default-attributes

```
@param (map) element-attributes
@param (map) element-props
{:class (keyword or keywords in vector)(opt)
 :disabled? (boolean)(opt)}
```

```
@usage
(default-attributes {...} {...})
```

```
@example
(default-attributes {...} {:class :my-class :disabled? true})
=>
{:class :my-element
 :data-disabled true}
```

```
@example
(default-attributes {...} {:class [:my-class] :disabled? true})
=>
{:class [:my-element]
 :data-disabled true}
```

```
@return (map)
{:class (keyword or keywords in vector)
 :data-disabled (boolean)}
```

<details>
<summary>Source code</summary>

```
(defn default-attributes
  [element-attributes {:keys [class disabled?]}]
  (merge element-attributes {:class         class
                             :data-disabled disabled?}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [default-attributes]]))

(pretty-css.api/default-attributes ...)
(default-attributes                ...)
```

</details>

---

### font-attributes

```
@param (map) element-attributes
@param (map) element-props
{:font-size (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :font-weight (keyword)(opt)
  :inherit, :extra-light, :light, :normal, :medium, :bold, :extra-bold
 :line-height (keyword)(opt)}
  :inherit, :native, :text-block, :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
```

```
@usage
(font-attributes {...} {...})
```

```
@example
(font-attributes {...} {:font-size :s :font-weight :bold :line-height :text-block})
=>
{:data-font-size   :s
 :data-font-weight :bold
 :data-line-height :text-block}
```

```
@return (map)
{:data-font-size (keyword)
 :data-font-weight (keyword)
 :data-line-height (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn font-attributes
  [element-attributes {:keys [font-size font-weight line-height]}]
  (merge element-attributes {:data-font-size   font-size
                             :data-font-weight font-weight
                             :data-line-height line-height}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [font-attributes]]))

(pretty-css.api/font-attributes ...)
(font-attributes                ...)
```

</details>

---

### icon-attributes

```
@param (map) element-attributes
@param (map) element-props
{:icon-color (keyword or string)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :icon-family (keyword)(opt)
 :icon-size (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(icon-attributes {...} {...})
```

```
@example
(icon-attributes {...} {:icon-color :default :icon-family :my-icon-family :icon-size :s})
=>
{:data-icon-color  :default
 :data-icon-family :my-icon-family
 :data-icon-size   :s}
```

```
@return (map)
{:data-icon-family (keyword)
 :data-icon-size (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn icon-attributes
  [element-attributes {:keys [icon-color icon-family icon-size]}]
  (-> (merge element-attributes {:data-icon-family icon-family
                                 :data-icon-size   icon-size})
      (apply-color :icon-color :data-icon-color icon-color)))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [icon-attributes]]))

(pretty-css.api/icon-attributes ...)
(icon-attributes                ...)
```

</details>

---

### indent-attributes

```
@param (map) element-attributes
@param (map) element-props
{:indent (map)(opt)
  {:bottom (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :left (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :right (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :top (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
```

```
@usage
(indent-attributes {...} {:indent {...}})
```

```
@example
(indent-attributes {...} {:indent {:horizontal :xxl :left :xs}})
=>
{:data-indent-horizontal :xxl
 :data-indent-left       :xs}
```

```
@return (map)
```

<details>
<summary>Source code</summary>

```
(defn indent-attributes
  [element-attributes {:keys [indent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-indent-" (name key))) value))]
         (merge element-attributes (reduce-kv f {} indent))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [indent-attributes]]))

(pretty-css.api/indent-attributes ...)
(indent-attributes                ...)
```

</details>

---

### marker-attributes

```
@param (map) element-attributes
@param (map) element-props
{:marker-color (keyword)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :marker-position (keyword)(opt)
  :tl, :tr, :br, :bl}
```

```
@usage
(marker-attributes {...} {...})
```

```
@example
(marker-attributes {...} {:marker-color :primary :marker-position :tr})
=>
{:data-marker-color    :primary
 :data-marker-position :tr}
```

```
@return (map)
{:data-marker-color (keyword)
 :data-marker-position (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn marker-attributes
  [element-attributes {:keys [marker-color marker-position]}]
  (merge element-attributes {:data-marker-color    marker-color
                             :data-marker-position marker-position}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [marker-attributes]]))

(pretty-css.api/marker-attributes ...)
(marker-attributes                ...)
```

</details>

---

### outdent-attributes

```
@param (map) element-attributes
@param (map) element-props
{:outdent (map)(opt)
  {:bottom (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :left (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :right (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :top (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
```

```
@usage
(outdent-attributes {...} {:outdent {...}})
```

```
@example
(outdent-attributes {...} {:outdent {:horizontal :xxl :left :xs}})
=>
{:data-outdent-horizontal :xxl
 :data-outdent-left       :xs}
```

```
@return (map)
```

<details>
<summary>Source code</summary>

```
(defn outdent-attributes
  [element-attributes {:keys [outdent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-outdent-" (name key))) value))]
         (merge element-attributes (reduce-kv f {} outdent))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [outdent-attributes]]))

(pretty-css.api/outdent-attributes ...)
(outdent-attributes                ...)
```

</details>

---

### text-attributes

```
@param (map) element-attributes
@param (map) element-props
{:text-direction (keyword)(opt)
  :normal, :reversed
 :text-overflow (keyword)(opt)
  :ellipsis, :hidden, :no-wrap, :wrap}
```

```
@usage
(text-attributes {...} {...})
```

```
@example
(text-attributes {...} {:text-direction :reversed :text-overflow :ellipsis})
=>
{:data-text-direction :default
 :data-text-overflow  :ellipsis}
```

```
@return (map)
{:data-text-direction (keyword)
 :data-text-overflow (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn text-attributes
  [element-attributes {:keys [text-direction text-overflow]}]
  (merge element-attributes {:data-text-direction text-direction
                             :data-text-overflow  text-overflow}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [text-attributes]]))

(pretty-css.api/text-attributes ...)
(text-attributes                ...)
```

</details>

---

This documentation is generated by the [docs-api](https://github.com/bithandshake/docs-api) engine

