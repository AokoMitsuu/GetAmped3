	� G (/efx/etc/efx_soldier/auto_gun_body_m.skn k�       �X[o�����S1��i���\�������b#@��<�8Q$C��B@$mK��m��I�>�[�dK�.A��uW��i�|���sx�Lٮ��0D������_���m�\CSDk$.�]d���������v�4�OU2.R�-;�)�2����S���� ���**c�(��S��m6�^Y���ʩ�\�����W��Y��u�'�:���`;ݮ�(�c�0_xsv&�#���@<�:�PaWM߬Y��k��5�l#^$Q�Y��ٙb1�������?����S$Y)��u��[4~��1_*P�9��[����T���V�fg\��yY�&�J[���gb����䘺�e�c��b�7����r]�?
4,9/f��#�.��*Ĉ[3Ѻ�/��F��|�=�n��o6���!C��v�¹��K�0_�`4�tk��s��o��(�s3|vUI,��R�r�G�g�:��u���e�`���N�ѱ��}�"Y���3��&0(�)V-pyZU������9�!�}�b�(����Qa"4�
���0Z���.)��t�F�x�u=Gӛ���@�W�(.���ˬ8��*t�cC#�ۋL�a&��CZvi�JԵ�J�X�{tg��pt����o�:gX�~���,pQ$I~BO&�E"�w������?<�پb�"F���4�x�k��aLܾp����:�ko2��]��wj|{3R�}�q�@S�s����w��!��M�0�W��Û.�����Tb�n��=�>�|��Z��8I�ak3�@b/k�)z�5��D�_Q��o���� �P�� ��EN>+(���-�����cJ���V���<7�E??�@3�zP.����y�87û�`;~�?�~?�3�䭝��]��L�?/��+K��O)��s��d��!R�R��ѓ����o;���M
���+���U ��L������ʄ��'7v���nl��A�oP��ԃ��F�>�����{A��`�v��(��w�����_�ur�DG�	��4/b��-�r��#��G)y0�JD��ȕ�,��5M�뮚�g����x��|���C��7�eU�L�A����&"�B�t��$����cR]�:jk�J�;4�6 U��OFp�[z�-�l9v�aL�jqu�Ju���hU�z���x/l�$�E�a���3 H�.#e؊�[�I���N�~OnU>��K;EH��*Ԥ�[`��`�q0|�i��vZ���l��u1&]�O�fV� �F0Gm�u�I����c�ݿd���gH���dJ�
B�`Q/.
��bh�������/��fk��l]�R5+ᗶFO�.��Mp�yN�17����S��%A�k8Rrd@����rX������k���>�"
{�����Y�=��)2���Is��gC���EUa���5% ��K � :ι���B�Z<��l��h!���Y\F�q&��G�����}O����K)����C6R(�M�-(�6F���M2���㒀��9�"��Y���ݜ,�����9k�/�8P@(`�ˤ��׃ៃ���W���QÛA��w�>�`/�����S0�3����g��l8B�1���A��Fu���QhՄ%���G�53���}oCTج�LK��2��}:�n��@�#��SC�@���a�63�%�2-��c&�Ҽ�����|�|�M�����2��S�J�~ �����R�	�e��c��={A����1�̙S����t����
ˢ5e{�+�Yu�պ顅p��/����'����a����g��[��,�{I�nr��S�$�ׯ�?�0���� ���fw�B^�~���:��$N�UzƖ�,�,H^���#��GqNS)�P�$���$e��N�{����sȿ%6\�1 +5�l��4	�'�}����S�{������U�m"��H���KS��Q��b��1]�8 ��;�#iT�,��_��Gʧ�d�-��>+��=�f"�򄥝�:���:9�Qz �2�b��E�T�ors.�D\�4����g��QwdZ�G�q���W�	r>v�p��c��s6L��Fr_������c�x��   �  Lga2/stage/AmpedScript; e LIMIT Lkotori/gfx/KGLScriptModel; lazer bullet nozzle start approachtarget 	damagehit count over destroy broken setEdge (Z)V
 f s t 
setVisible
 f v t anime (FFFZ)V
 d x y<#�
A    enableAdjust (I)Z
 f } ~ lockItemScript (ZZ)V
 f � �
 H 0 3 setShadowState
 d � 3 enableCollision
 f � t generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/laser/rifle_sight_red.scm>�����ff    ?�  ?�  >�ff    ´       A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D (/efx/etc/efx_soldier/auto_gun_nozzle.scm=��;333    ?�  ?�  ?�  B�  B�       
start_rollD (/efx/etc/efx_soldier/auto_gun_bullet.scm>333=L��>��?�  ?�  ?�      B�  ´   setJudgmentApproachTarget (F)V
 f � �A�   setJudgmentKind
 f � 3 h startScript (Ljava/lang/String;)Z
 � � � getArgv ()Ljava/lang/Object;
 f � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � cos (F)F
 H � � sin
 H � �	 � Q  Lkotori/geom/Position; � dir	 � � 	 � S  (FFF)Lkotori/geom/Vector3D;
 � + � Lkotori/geom/KMath; � getDir (Lkotori/geom/Vector3D;)F
 � � � R2D
 � � � Ljava/lang/Math; � round (F)I
 � � �C4  C�  BH  =���B�   (FF)V
 d x �>L���� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � ��� �D  /efx/gun/shell_gatlinggun_a3.scm�            ?&ff?&ff?&ff            � hit_b2   d�   �  �FP �       ,�>�        �� �D  /efx/gun/shell_gatlinggun_a3.scm�            ?&ff?&ff?&ff            �   d�    �  �FP �       ,�>�        D /efx/gun/flush_doublegun.scm=��Ϳ���    >�  >�  >�      B�      D /efx/smoke/smoke_gun.ptc=��Ϳ���    @   @   @       C4       D  /efx/gun/cartridge_doublegun.scm>L�ͽ���    ?   ?   ?L��    ´        	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � Lga2/stage/atk/AttackParam; � hitscp	 � � 
 hit_bb hit_b2 attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-    ?333?�   vibe (FFFFI)V
 d � �;� 	playSound
 d � 3 right2 temp
 d � � Ljava/lang/Integer; � intValue
 � � 2 tempInt (I)Ljava/lang/Integer;
 d � �<��
 kill
 f � 1 clearJudgmentKind
 f � 1 lockTimeCount
 f � t
 � � 1D /efx/spark/spark_of_fire3.ptc�            ?�  ?�  ?�               D /efx/flash/glow01_yellow5.ptc�            ?�  ?�  ?�               -    >�      ��?5�?5�         Lkotori/data/KGLGenerationData; � hpb (FFF)V
 � � �´   scale
 � �?�33B�  ?�ff?fffD /efx/explode/shockw1.scm�            ?��?��?��             D /efx/explode/exp_sphere1_1.scm�            >�(�>�(�>�(�             D /efx/smoke/smoke_black.scm�            ?�ff?�ff?�ff    ´       D /efx/smoke/smoke_black.scm�            @33@33@33    ´        	start_red-    >�      ��?5�?5�        ��  -    >�      ��?5�?5�        @���@33-    >�      ��?5�?5�         
stopScript
 f 1 f g  i h j h k h - 1   �� �  l 1  �*� uW*� wW*{|� zW*� � 
*� �W*d� �W*� wW*� �W*� �W*�� ��*��� ��*�� ��* ȶ �W*� � *� �W*�� �W*� �W� *� W�  m 1 �Z<�� ��� �W*� �M*� �N,-� �W,� �-� �� �j,� �-� �� �jf8,� �-� �� �j,� �-� �� �jb8,� �W,� �� �� ��8��� 
�f8��� 
�8� t��� t�8�Й ��jf� W��jb8*Ŷ �W* ȶ �W�:� �:� �W*� � 
�:	� �:	�:
�:�:66� w*� �Wd� 	ڵ �� 
	۵ �*�	� �W*� �Wp� *{�\d� �W* ȶ �W*
� �W*� �W*� �W*d� �W�W����� �l� �W*�� �W*� � *�� �W*� �W� 76*� �:� 
� �6
`� � *� W*
`� �W*� W�  n 1  +*� �W*��\ ȶ �W* ȶ �W*�� �W*� �W�  o 1  ]*�� �W<*� �M,� ,� �<� � *� W>� **� �M,� �<� � *� W*� �W*d� �W�W� `���*� �W�  p 1 �*� �W*� wW*� �W*� � *� �W*� �W*� �W�� 	�� �W�� 	�� �W�� 	�� �W�L�M*��� �W+� W+�W*+� �W+�� W+�W*+� �W+� W+�W*+� �W+�� W+�W*+� �W*� �W*	� �W*
� �W*� �W*� �W*� �W*� �W*� �W+� W+�W*+� �W+�\� W+�W*+� �W*� �W*� �W*� �W,�� W,�W*,� �W,�� W,�W*,� �W,�� W,�W*,� �W*� �W*� �W*� �W+�� W+�W*+� �W+� W+�W*+� �W*� �W*� �W*� �W*�W�  q 1  *� W�  r 1  *� W� ������^x?s4�np>�Ղ?��>��� � 
HP_nojudge�BH  � 	collision�	1    >�ff    >�  >�ff>�  