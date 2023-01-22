
# pretty-css.api isomorphic namespace

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > pretty-css.api

### Index

- [adaptive-border-radius](#adaptive-border-radius)

- [apply-color](#apply-color)

- [apply-dimension](#apply-dimension)

- [apply-preset](#apply-preset)

- [badge-attributes](#badge-attributes)

- [block-max-size-attributes](#block-max-size-attributes)

- [block-min-size-attributes](#block-min-size-attributes)

- [block-size-attributes](#block-size-attributes)

- [border-attributes](#border-attributes)

- [border-radius-attributes](#border-radius-attributes)

- [bubble-attributes](#bubble-attributes)

- [color-attributes](#color-attributes)

- [content-max-size-attributes](#content-max-size-attributes)

- [content-min-size-attributes](#content-min-size-attributes)

- [content-size-attributes](#content-size-attributes)

- [default-attributes](#default-attributes)

- [effect-attributes](#effect-attributes)

- [element-max-size-attributes](#element-max-size-attributes)

- [element-min-size-attributes](#element-min-size-attributes)

- [element-size-attributes](#element-size-attributes)

- [font-attributes](#font-attributes)

- [icon-attributes](#icon-attributes)

- [indent-attributes](#indent-attributes)

- [link-attributes](#link-attributes)

- [marker-attributes](#marker-attributes)

- [outdent-attributes](#outdent-attributes)

- [progress-attributes](#progress-attributes)

- [text-attributes](#text-attributes)

- [thumbnail-size-attributes](#thumbnail-size-attributes)

- [tooltip-attributes](#tooltip-attributes)

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

### block-max-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:max-height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :max-width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(block-max-size-attributes {...} {...})
```

```
@example
(block-max-size-attributes {...} {:max-height :xl, :max-width :xl})
=>
{:data-max-block-height :xl
 :data-max-block-width  :xl}
```

```
@return (map)
{:data-block-max-height (keyword)
 :data-block-max-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn block-max-size-attributes
  [element-attributes {:keys [max-height max-width]}]
  (merge element-attributes {:data-block-max-height max-height
                             :data-block-max-width  max-width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [block-max-size-attributes]]))

(pretty-css.api/block-max-size-attributes ...)
(block-max-size-attributes                ...)
```

</details>

---

### block-min-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:min-height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :min-width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(block-min-size-attributes {...} {...})
```

```
@example
(block-min-size-attributes {...} {:min-height :xl, :min-width :xl})
=>
{:data-block-min-height :xl
 :data-block-min-width  :xl}
```

```
@return (map)
{:data-block-min-height (keyword)
 :data-block-min-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn block-min-size-attributes
  [element-attributes {:keys [min-height min-width]}]
  (merge element-attributes {:data-block-min-height min-height
                             :data-block-min-width  min-width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [block-min-size-attributes]]))

(pretty-css.api/block-min-size-attributes ...)
(block-min-size-attributes                ...)
```

</details>

---

### block-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(block-size-attributes {...} {...})
```

```
@example
(block-size-attributes {...} {:height :xl, :width :xl})
=>
{:data-block-height :xl
 :data-block-width  :xl}
```

```
@return (map)
{:data-block-height (keyword)
 :data-block-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn block-size-attributes
  [element-attributes {:keys [height width]}]
  (merge element-attributes {:data-block-height height
                             :data-block-width  width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [block-size-attributes]]))

(pretty-css.api/block-size-attributes ...)
(block-size-attributes                ...)
```

</details>

---

### border-attributes

```
@param (map) element-attributes
@param (map) element-props
{:border-color (keyword or string)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :border-radius (map)(opt)
  {:tl (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :tr (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :br (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :bl (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :all (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
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
(border-attributes {...} {:border-color :default :border-width :s})
=>
{:data-border-color :default
 :data-border-width :s}
```

```
@return (map)
{:data-border-position (keyword)
 :data-border-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn border-attributes
  [element-attributes {:keys [border-color border-position border-width] :as element-props}]
  (-> (merge element-attributes {:data-border-position border-position
                                 :data-border-width    border-width})
      (border-radius-attributes element-props)
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

### border-radius-attributes

```
@param (map) element-attributes
@param (map) element-props
{:border-radius (map)(opt)
  {:tl (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :tr (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :br (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :bl (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :all (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}}
```

```
@usage
(border-radius-attributes {...} {:border-radius {...}})
```

```
@example
(border-radius-attributes {...} {:border-radius {:tr :xxl :tl :xs}})
=>
{:data-border-radius-tr :xxl
 :data-border-radius-tl :xs}
```

```
@return (map)
```

<details>
<summary>Source code</summary>

```
(defn border-radius-attributes
  [element-attributes {:keys [border-radius]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-border-radius-" (name key))) value))]
         (merge element-attributes (if (map?           border-radius)
                                       (reduce-kv f {} border-radius)))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [border-radius-attributes]]))

(pretty-css.api/border-radius-attributes ...)
(border-radius-attributes                ...)
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

### content-max-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:max-height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :max-width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(content-max-size-attributes {...} {...})
```

```
@example
(content-max-size-attributes {...} {:max-height :xl, :max-width :xl})
=>
{:data-content-max-height :xl
 :data-content-max-width  :xl}
```

```
@return (map)
{:data-content-max-height (keyword)
 :data-content-max-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn content-max-size-attributes
  [element-attributes {:keys [max-height max-width]}]
  (merge element-attributes {:data-content-max-height max-height
                             :data-content-max-width  max-width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [content-max-size-attributes]]))

(pretty-css.api/content-max-size-attributes ...)
(content-max-size-attributes                ...)
```

</details>

---

### content-min-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:min-height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :min-width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(content-min-size-attributes {...} {...})
```

```
@example
(content-min-size-attributes {...} {:min-height :xl, :min-width :xl})
=>
{:data-content-min-height :xl
 :data-content-min-width  :xl}
```

```
@return (map)
{:data-content-min-height (keyword)
 :data-content-min-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn content-min-size-attributes
  [element-attributes {:keys [min-height min-width]}]
  (merge element-attributes {:data-content-min-height min-height
                             :data-content-min-width  min-width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [content-min-size-attributes]]))

(pretty-css.api/content-min-size-attributes ...)
(content-min-size-attributes                ...)
```

</details>

---

### content-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(content-size-attributes {...} {...})
```

```
@example
(content-size-attributes {...} {:height :xl, :width :xl})
=>
{:data-content-height :xl
 :data-content-width  :xl}
```

```
@return (map)
{:data-content-height (keyword)
 :data-content-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn content-size-attributes
  [element-attributes {:keys [height width]}]
  (merge element-attributes {:data-content-height height
                             :data-content-width  width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [content-size-attributes]]))

(pretty-css.api/content-size-attributes ...)
(content-size-attributes                ...)
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
  [element-attributes {:keys [class disabled?] :as element-props}]
  (letfn [(f0 [%] (if % (if (vector? %) % [%]) []))
          (f1 []  (vec  (concat (-> element-attributes :class f0)
                                (-> element-props      :class f0))))]
         (merge element-attributes {:class (f1)
                                    :data-disabled disabled?})))
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

### effect-attributes

```
@param (map) element-attributes
@param (map) element-props
{:click-effect (keyword)(opt)
 :hover-effect (keyword)(opt)
 :reveal-effect (keyword)(opt)}
```

```
@usage
(effect-attributes {...} {...})
```

```
@example
(effect-attributes {...} {:click-effect :opacity :hover-effect :opacity})
=>
{:data-click-effect :opacity
 :data-hover-effect :opacity}
```

```
@return (map)
{:data-click-effect (keyword)
 :data-hover-effect (keyword)
 :data-reveal-effect (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn effect-attributes
  [element-attributes {:keys [click-effect hover-effect reveal-effect]}]
  (merge element-attributes {:data-click-effect  click-effect
                             :data-hover-effect  hover-effect
                             :data-reveal-effect reveal-effect}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [effect-attributes]]))

(pretty-css.api/effect-attributes ...)
(effect-attributes                ...)
```

</details>

---

### element-max-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:max-height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :max-width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(element-max-size-attributes {...} {...})
```

```
@example
(element-max-size-attributes {...} {:max-height :xl, :max-width :xl})
=>
{:data-element-max-height :xl
 :data-element-max-width  :xl}
```

```
@return (map)
{:data-element-max-height (keyword)
 :data-element-max-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn element-max-size-attributes
  [element-attributes {:keys [max-height max-width]}]
  (merge element-attributes {:data-element-max-height max-height
                             :data-element-max-width  max-width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [element-max-size-attributes]]))

(pretty-css.api/element-max-size-attributes ...)
(element-max-size-attributes                ...)
```

</details>

---

### element-min-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:min-height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :min-width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(element-min-size-attributes {...} {...})
```

```
@example
(element-min-size-attributes {...} {:min-height :xl, :min-width :xl})
=>
{:data-element-min-height :xl
 :data-element-min-width  :xl}
```

```
@return (map)
{:data-element-min-height (keyword)
 :data-element-min-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn element-min-size-attributes
  [element-attributes {:keys [min-height min-width]}]
  (merge element-attributes {:data-element-min-height min-height
                             :data-element-min-width  min-width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [element-min-size-attributes]]))

(pretty-css.api/element-min-size-attributes ...)
(element-min-size-attributes                ...)
```

</details>

---

### element-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(element-size-attributes {...} {...})
```

```
@example
(element-size-attributes {...} {:height :xl, :width :xl})
=>
{:data-element-height :xl
 :data-element-width  :xl}
```

```
@return (map)
{:data-element-height (keyword)
 :data-content-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn element-size-attributes
  [element-attributes {:keys [height width]}]
  (merge element-attributes {:data-element-height height
                             :data-element-width  width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [element-size-attributes]]))

(pretty-css.api/element-size-attributes ...)
(element-size-attributes                ...)
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
 :letter-spacing (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl, :auto
 :line-height (keyword)(opt)}
  :inherit, :native, :text-block, :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
```

```
@usage
(font-attributes {...} {...})
```

```
@example
(font-attributes {...} {:font-size :s :font-weight :bold :letter-spacing :s :line-height :text-block})
=>
{:data-font-size   :s
 :data-font-weight :bold
 :data-letter-spacing :s
 :data-line-height :text-block}
```

```
@return (map)
{:data-font-size (keyword)
 :data-font-weight (keyword)
 :data-letter-spacing (keyword)
 :data-line-height (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn font-attributes
  [element-attributes {:keys [font-size font-weight letter-spacing line-height]}]
  (merge element-attributes {:data-font-size      font-size
                             :data-font-weight    font-weight
                             :data-letter-spacing letter-spacing
                             :data-line-height    line-height}))
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
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :horizontal (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :vertical (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :all (keyword)(opt)
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
         (merge element-attributes (if (map?           indent)
                                       (reduce-kv f {} indent)))))
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

### link-attributes

```
@param (map) element-attributes
@param (map) element-props
{:href (string)(opt)
 :target (keyword)(opt)
  :blank, :self}
```

```
@usage
(link-attributes {...} {...})
```

```
@example
(link-attributes {...} {:href "/my-link" :target :blank})
=>
{:href   "/my-link"
 :target :_blank}
```

```
@return (map)
{:href (string)
 :target (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn link-attributes
  [element-attributes {:keys [href target]}]
  (merge element-attributes {:href   href
                             :target (case target :blank :_blank :self :_self nil)}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [link-attributes]]))

(pretty-css.api/link-attributes ...)
(link-attributes                ...)
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
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :horizontal (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :vertical (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
   :all (keyword)(opt)
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
         (merge element-attributes (if (map?           outdent)
                                       (reduce-kv f {} outdent)))))
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

### progress-attributes

```
@param (map) element-attributes
{:style (map)(opt)}
@param (map) element-props
{:progress (percent)(opt)
 :progress-color (keyword)(opt)
 :progress-direction (keyword)(opt)
  :ltr, :rtl, :ttb, :btt
 :progress-duration (ms)(opt)}
```

```
@usage
(progress-attributes {...} {...})
```

```
@example
(progress-attributes {...} {:progress           "50"
                            :progress-color     :primary
                            :progress-direction :ltr
                            :progress-duration  250})
=>
{:data-progress-direction :ltr
 :style {"background-image"    "linear-gradient(var(--fill-color-primary), var(--fill-color-primary))"
         "--progress"          "50%"
         "--progress-duration" "250ms"}}
```

```
@return (map)
{:data-progress-direction (keyword)
 :style (map)
  {"--progress" (string)
   "--progress-duration" (string)}}
```

<details>
<summary>Source code</summary>

```
(defn progress-attributes
  [{:keys [style] :as element-attributes} {:keys [progress progress-color progress-direction progress-duration]}]
  (assoc element-attributes :style (merge style (if progress          {"--progress"          (str progress          "%")})
                                                (if progress-duration {"--progress-duration" (str progress-duration "ms")})
                                                (if progress-color    {"background-image"    (progress-color-f progress-color)}))
                            :data-progress-direction progress-direction))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [progress-attributes]]))

(pretty-css.api/progress-attributes ...)
(progress-attributes                ...)
```

</details>

---

### text-attributes

```
@param (map) element-attributes
@param (map) element-props
{:selectable? (boolean)(opt)
 :text-direction (keyword)(opt)
  :normal, :reversed
 :text-overflow (keyword)(opt)
  :ellipsis, :hidden, :no-wrap, :wrap
 :text-transform (keyword)(opt)
  :capitalize, :lowercase, :uppercase}
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
{:data-selectable (boolean)
 :data-text-direction (keyword)
 :data-text-overflow (keyword)}
 :data-text-transform (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn text-attributes
  [element-attributes {:keys [selectable? text-direction text-overflow text-transform]}]
  (merge element-attributes {:data-selectable      selectable?
                             :data-text-direction  text-direction
                             :data-text-overflow   text-overflow
                             :data-text-transform  text-transform}))
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

### thumbnail-size-attributes

```
@param (map) element-attributes
@param (map) element-props
{:height (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl
 :width (keyword)(opt)
  :xxs, :xs, :s, :m, :l, :xl, :xxl, :3xl, :4xl, :5xl}
```

```
@usage
(thumbnail-size-attributes {...} {...})
```

```
@example
(thumbnail-size-attributes {...} {:height :xl, :width :xl})
=>
{:data-thumbnail-height :xl
 :data-thumbnail-width  :xl}
```

```
@return (map)
{:data-thumbnail-height (keyword)
 :data-thumbnail-width (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn thumbnail-size-attributes
  [element-attributes {:keys [height width]}]
  (merge element-attributes {:data-thumbnail-height height
                             :data-thumbnail-width  width}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [thumbnail-size-attributes]]))

(pretty-css.api/thumbnail-size-attributes ...)
(thumbnail-size-attributes                ...)
```

</details>

---

### tooltip-attributes

```
@param (map) element-attributes
@param (map) element-props
{:tooltip-content (string)(opt)
 :tooltip-position (keyword)(opt)}
```

```
@usage
(tooltip-attributes {...} {...})
```

```
@example
(tooltip-attributes {...} {:tooltip-content "My tooltip" :tooltip-position :left})
=>
{:data-tooltip-content  "My tooltip"
 :data-tooltip-position :left}
```

```
@return (map)
{:data-tooltip-content (string)
 :data-tooltip-position (keyword)}
```

<details>
<summary>Source code</summary>

```
(defn tooltip-attributes
  [element-attributes {:keys [tooltip-content tooltip-position]}]
  (merge element-attributes {:data-tooltip-content  tooltip-content
                             :data-tooltip-position tooltip-position}))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [pretty-css.api :refer [tooltip-attributes]]))

(pretty-css.api/tooltip-attributes ...)
(tooltip-attributes                ...)
```

</details>

---

This documentation is generated by the [docs-api](https://github.com/bithandshake/docs-api) engine

