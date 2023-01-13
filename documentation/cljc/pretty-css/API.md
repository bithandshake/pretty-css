
# pretty-css.api isomorphic namespace

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > pretty-css.api

### Index

- [apply-color](#apply-color)

- [apply-dimension](#apply-dimension)

- [apply-preset](#apply-preset)

- [badge-attributes](#badge-attributes)

- [default-attributes](#default-attributes)

- [indent-attributes](#indent-attributes)

- [marker-attributes](#marker-attributes)

- [outdent-attributes](#outdent-attributes)

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
@param (map) element-props
{:badge-color (keyword)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :badge-content (string)
 :badge-position (keyword)
  :tl, :tr, :br, :bl}
```

```
@usage
(badge-attributes {...})
```

```
@example
(badge-attributes {:badge-color :primary :badge-content "420" :badge-position :tr})
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
  [{:keys [badge-color badge-content badge-position]}]
  {:data-badge-content  badge-content
   :data-badge-color    badge-color
   :data-badge-position badge-position})
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

### default-attributes

```
@param (map) element-props
{:class (keyword or keywords in vector)(opt)
 :disabled? (boolean)(opt)}
```

```
@usage
(default-attributes {...})
```

```
@example
(default-attributes {:class :my-class :disabled? true})
=>
{:class :my-element
 :data-disabled true}
```

```
@example
(default-attributes {:class [:my-class] :disabled? true})
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
  [{:keys [class disabled?]}]
  {:class         class
   :data-disabled disabled?})
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

### indent-attributes

```
@param (map) element-props
{:indent (map)(opt)
  {:bottom (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl
   :left (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl
   :right (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl
   :top (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl}}
```

```
@usage
(indent-attributes {:indent {...}})
```

```
@example
(indent-attributes {:indent {:horizontal :xxl :left :xs}})
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
  [{:keys [indent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-indent-" (name key))) value))]
         (reduce-kv f {} indent)))
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
@param (map) element-props
{:marker-color (keyword)(opt)
  :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
 :marker-position (keyword)(opt)
  :tl, :tr, :br, :bl}
```

```
@usage
(marker-attributes {...})
```

```
@example
(marker-attributes {:marker-color :primary :marker-position :tr})
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
  [{:keys [marker-color marker-position]}]
  {:data-marker-color    marker-color
   :data-marker-position marker-position})
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
@param (map) element-props
{:outdent (map)(opt)
  {:bottom (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl
   :left (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl
   :right (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl
   :top (keyword)(opt)
    :xxs, :xs, :s, :m, :l, :xl, :xxl}}
```

```
@usage
(outdent-attributes {:outdent {...}})
```

```
@example
(outdent-attributes {:outdent {:horizontal :xxl :left :xs}})
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
  [{:keys [outdent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-outdent-" (name key))) value))]
         (reduce-kv f {} outdent)))
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

This documentation is generated by the [docs-api](https://github.com/bithandshake/docs-api) engine

