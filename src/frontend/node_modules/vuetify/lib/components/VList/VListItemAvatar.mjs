import { createVNode as _createVNode, mergeProps as _mergeProps, resolveDirective as _resolveDirective } from "vue";
// Components
import { makeVAvatarProps, VAvatar } from "../VAvatar/VAvatar.mjs"; // Utilities

import { defineComponent, useRender } from "../../util/index.mjs";
export const VListItemAvatar = defineComponent({
  name: 'VListItemAvatar',
  props: makeVAvatarProps(),

  setup(props, _ref) {
    let {
      slots
    } = _ref;
    useRender(() => _createVNode(VAvatar, _mergeProps({
      "class": ['v-list-item-avatar', {
        'v-list-item-avatar--start': props.start,
        'v-list-item-avatar--end': props.end
      }]
    }, props), slots));
    return {};
  }

});
//# sourceMappingURL=VListItemAvatar.mjs.map