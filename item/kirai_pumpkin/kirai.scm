	� G 7/efx/etc/efx_magic_houki/throw_objs/jack_o_lantern1.mot��       �U�o�D���d�䴙_:@V>4[U�-m��]�sr�c���M�"5)�B�*�&4��$*^�������/E�/x��N�4CY��~�����y��_��]+��(��ձ���^�O�t*�"-������nw�9����8|���yQ4[~��L�j��K�q�w�߿�����u�3%�2�6�N�I��|D� [rQ�6)���Dq��Ѱ��Z��UQ`��N2�Cjy�=�;o|��TY)��tk�-�����r2(%�E ӃwNcD�H�_)#�`$�I�k�ʄ�l�CT��|*�]��l� '�2vq�(�\ι�xی�����h;(lh׍
���Z&{��BE���5将��� ��K��T�7�t��,o"G��Ϛ\��X�As@`�HH����Vu�+�`�*U�8jXk�����79��_��W�m.����L��/�$iZ��8�F����>���i��0)��
�^(uI����n�A(.���evp�|����͑-a��B:p-[1T=;/<���e��(x�yӜ��$�m��d`�l�W[�8ud��-o��K���9C��aZ�??�v��N�Obb>
CeAZ��<����d��&���$�%	��4|�w��1�mDDI��~��jkt�#/@!/1�Q��hp(���?E�[�5�Ή��M�"1�BN�Փ��q���f]XH��srH\�nцj������&4d!f�6��?�$��~��x��ۨ����;ã����p�s$,|��.�y�5�����r1�����Z� n+������&<��\��h��"?%d�#�A��Έ�����8���W���w�� �t�O�|~r0�s/�G_p��A�?���q3׋��?|�G����O���8����;>�8�d=I��JP���� ������:��F�'�Bq�-�Q�ݶ��Y>U��q���뤻�F�$�}u׽�k)�B�u���"x;����7�WB��l��<���K(5o�������i/�(E�&��VN��$L/�%�j��-I����b��j-cʰ9 ��k��J�E�����ɤ�z��Q`ňh���(�W�V���6��o-��v^�(qؙ���	��?���y�G���F)9Q
@O��@�x����k�o딴�צW-7ȕK��kbwj�oz'D   X  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; red2�� kotori.data.MixtureData� /efx/mix/bar1.agi�� i Lkotori/gfx/KGLScriptModel; fire Lkotori/data/KGLGenerationData; glowD /efx/flash/glow01_red6.ptc�            @
Nx@
Nx@
Nx             start start1 movement (FF)V exp approach timeout hit broken setShadowState
 d y 3 setEdge (Z)V
 f { | 
setVisible
 f ~ | generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/fire/fire_ball.scm�    ����    @   @   @                	kotefire5 /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D /efx/flash/glow01_white3.ptc�            ?�  ?�  ?�               rndf (F)F
 H � � m scale (F)V
 � � �?�  AP  ?�ff
 H 0 3D /efx/fire/fire_ball.scm�    ����    @   @   @                sin
 H � � Lkotori/geom/KMath; � fPI2	 � � �� kotori.geom.Quaternion�;�=��?�-��< Lkotori/geom/Quaternion; � getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �C4   rotate (Lkotori/geom/Quaternion;I)V
 f � � 
setMixture (Lkotori/data/MixtureData;)V
 d � � mixtureMotion (IFFF)V
 d � ���   k kill
 � � 1 yquake
 d � 3 	playSound (II)V
 d � �D /efx/explode/exp_sphere1.scm�            ?���?���?���              attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �� ga2.stage.atk.ExplodeAttack d2� � hit_bc   d�� �-            �� d2 �  d�� �
 f � 1 f h g j  V  l k n m	 - 1  	i� o��  p 1  �*� zW*� }W*� W*��� ���� f�p� 
*�� �W�p� �� �b� �W*�� �W�p� ��� �b� �W*�� �W�*��� W*2� �W�Y`�W����  q 1  *� zW*� }W*��� �W�  r s  7\F#� ��%nj� �j8�:�� �W*2� �W�$b��%r��  t 1  k*� �W*� � �W*�� �W�� 	�� �W*2� �W*� �W*!� �W*�� �W*��� �W*��� �W*d� �W*� W*d� �W*� �W�  u 1  *� W�  v 1  *� W�  w 1  *� W�  x 1  *� W� ����            ?6�>�@�?sk�  