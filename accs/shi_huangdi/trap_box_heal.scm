	� G 1/efx/stageitem/trap/treasure_box/treasure_box.mot��       �U�n�@��H��n���#!du��mZ��T�F{��˞���,Z�Bl��!V�B� �cB��?�$Ny�ؑ&�Μs�N�xxİoE���JĐ���`�c�$`F�D���Lt(�!QlL=e�h��9bβ�B��X6�� ��`��������o�}}�qz��˧��+���^�����ۻ�r�\2�G}�����u�j���C��w`�h��\��[�GR�B<#�|kM����wQ4P�_ӻc�¢��\3���&��#7���X�Kz�`5v�%a�SY����=:R�S��ޯ9$D���(�Z����%U���NrF�%B�!�˥ E��U�_�0;%�X��ʌ���diR���&�7�(���3O@�8<X�zݣ��Ak�$so����c��{��m��7����xCY8!�����ĺ�0.w�pj���7�d>�e�㽽����@w�6�t$�r���+���bL���hh���!X=��>�R@�.��3�	oƬ!�XH�qK���H1����x 6����.F��J�J�C�K��z!u��v[�cQy�HE�t嚖�(]q�$ʈ� Mu�.�g"1��$e��a~V��_�E������MHt77�|��R�Da8���jU׹�5�
b��#��%M�'?�@�\d��`ý�W9��S�O��&�K��u�v�|�\�$�P�ꤨ�z~��X�i���|gRj�h�p�f�/#I?�wb���o   D  Lga2/stage/AmpedScript; e Lkotori/data/KGLGenerationData; glowD /efx/flash/glow01_yellow2.ptc�            ?�  ?�  ?�                ib_flashD */efx/stageitem/trap/item_box/ib_flash1.scm�            ?�  ?�  ?�                kirakiraD /efx/kirakira/03.ptc�            ?�  ?�  ?�                i start destroy broken open
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
 d � 1 f h g j g l g n  - 1  i� k�m��  o 1  �*� sW*� vW*� xW*� {W*2� sW*� vW*� }W*� W* �� �W*d� sW*3� �W*d� sW*� xW*� W� *� �W*�� �� �К��*� �W*� �W�  p 1  #*� �W*� �W*� �W*� �W*� �W*� W�  q 1  N*� �W*� �W*� �W*� �W*� sW*� xW*� }W*� W*,� �W*,� sW*� �W*� �W�  r 1  �*� vW*� sW*� vW*� }W*� W*� {W*� �W*2� sW*��� �W*2� sW*��� �W*2� sW*��� �W*l� sW*�� �W*� W*� xW*�� sW*� vW*�� sW*� �W*� �W*� �W� b���    >��    >���>��>�  � � 
HP_nojudge�@�  � collisiontype�    � 	collision�1    >aG�    >u>aG�>��