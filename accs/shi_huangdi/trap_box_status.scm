	� G 1/efx/stageitem/trap/treasure_box/treasure_box.mot��       �U�n�@��H��n���#!*��"M۴4j���&���1�=iUY��� �C�b���Ǆ">�;~�I��ؑ&�Μs�N�xxİoE���JĐ���`�c�$`F�D���Lt(�!QlL=e�h��9bβ�B��X6�� ��`����gӫ�7o�~y�az����W�k���^=�����۫r�\2�G}�����u�j���C��wa�h��B��[{�R�R<'�b{M����wQ4P�_ӻc�¢��\3���&��#7���X�Kz�`5v�%a�SY����=:R�S����9$D���(�Z����%U���NrN�%B�!�˥ E��U�_�0;#�X��ʌ{��diR���&�7�(���3�A�8<X�z���a��4s����c��w��m�4����xCY8!�����ĺ�0.w�pj�V��e2�2���~��b@ ��v��l:�x9oj�I�hp�_1&�	�{8�l�� �CaA) x�B���7c�G,��%�KM���\y��~J< �sfxM�p�|%w�ąj%����bd��:��[�cQyIE�t嚖�(]q�$ʈ� Mu�.�g"1��$e��e~V���E������MHt77�|��R�Da8��ĭ�,�s5kJ�?:|Gr�K�rO����u��<7��{M�r��(���M
��o�r���ir��5B�A������M�}!���r�=�mH��O�A�u�ɿ�P$�@�� �Uؾo   D  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; glowD /efx/flash/glow01_yellow2.ptc�            ?�  ?�  ?�                ib_flashD */efx/stageitem/trap/item_box/ib_flash1.scm�            ?�  ?�  ?�                kirakiraD /efx/kirakira/03.ptc�            ?�  ?�  ?�                i start destroy broken open
 H 0 3 
setVisible (Z)V
 f t u setEdge
 f w u setColor (FFFF)V
 d y z setRenderType
 d | 3 setBlend
 d ~ 3 	fadeColor (FFFFI)V
 d � � 	playSound
 d � 3
 H 0 1 (FFF)Lkotori/geom/Vector3D;
 H  � groundCheck (Lkotori/geom/Vector3D;)Z
 f � ����� setJudgmentKind
 f � 3 enableCollision
 f � u lockItemScript
 f � u lockTimeCount
 f � u clearJudgmentKind
 f � 1 kill
 f � 1 
stopScript
 f � 1 anime (F)V
 d � � shootCarryItem 5(ILkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)V
 f � �-    >L��    � d�@     d�   ����  .�    ,�>�        -    >L��    � d�@     d�   ����  .�    ,�>�        -    >L��    � d�@     d�   �  ��  .�    ,�>�         finishAllConnections
 d � 1 f h g j g l g n  - 1  i� k�m��  o 1  �*� sW*� vW*� xW*� {W*2� sW*� vW*� }W*� W* �� �W*d� sW*3� �W*d� sW*� xW*� W� *� �W*�� �� �К��*� �W*� �W�  p 1  #*� �W*� �W*� �W*� �W*� �W*� W�  q 1  N*� �W*� �W*� �W*� �W*� sW*� xW*� }W*� W*,� �W*,� sW*� �W*� �W�  r 1  �*� vW*� sW*� vW*� }W*� W*� {W*� �W*2� sW*H��� �W*2� sW*H��� �W*2� sW*H��� �W*l� sW*�� �W*� W*� xW*�� sW*� vW*�� sW*� �W*� �W*� �W� b��Q    >��    >���>��>�  � � 
HP_nojudge�@�  � collisiontype�    � 	collision�1    >aG�    >u>aG�>��