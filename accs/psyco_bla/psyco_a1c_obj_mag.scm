	� G   r�       �VmkG�,C��V�rr�w'�"���Ʀ��Z"J1�Ӟ�����n�X����4)��BH�h�[Zں�~	}�/]d���ݓd;I㦥���N���33������d�����.Y	�-bU��4knL#^95F���ug1���ck��EX�<�[?��B<ļ�.��IE�I��>���hs�p��X�gng��@'o�KL$�$߄�Z7�U ��(p��4iW}b���B��$1�����{�O�_]D-�XR����K�Z���L�Ʈ��Q�s�J��v'��7�WP��1'�T6!І���� /�7�ԫI�z�&���GyIs� `7��,]���b���Yl�>�����Hw����6xPϠI��k$6
*R�2���9�v}�r�}��-�I��%t	�,�ȒX{�6#
,0��&Қ���6�?R?\���$��dQ�6F�Y:���~�΢��%����/��`��^�m��� <��V�[kzͻ]�8�
���uh���Jh�r}B�Jζ��o�����z"�"���SW�?������O��3�yG���f���� ��3����P�9
z:���ʁ��tF�i�0�E�J�@o_��m-����jҸ��<�a�$����$���(�X�5��� �9˴G���J�'a�����z��&���m;��e�m��H���n�ɎH���p�{h�����|���_��y��������[����E�X�6����hy�d=�.��hB%^9d[�1��q����hhL�M���V �/i_(�F�I��߽�wwKގZKj�p���3��T!�qYՇ�����[��D�ˊaD2��`���LmÜc��r��&���Ump���g���8�%-(�Gy:N<I�QCN�3� ���>�gC���<D�ﴺmS������J���ē�WzC�ǌ�^$W�}�"�gI7������|��y�m�33�@��L�^_^�-\�+�
�����{���ǵS�v�*gs�o�����֤��\=���b����Bu��|}~vaiq���$���ٳ�i������<Cz��؈Y���?p��4�xUU����ѿ�������y6t�S<�I��
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_bla/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
 f x y isServer ()Z
 f { | getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f ~  '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 	playSound
 d � 3 getArgv ()Ljava/lang/Object;
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY2 (F)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/Position; � dir	 � � 	 � R  getDir (Lkotori/geom/Vector3D;)F
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � � min
 � � �B4   getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �B�   	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � startScript (Ljava/lang/String;)Z
 � � �-            ��� �D /accs/psyco_bla/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ���                        �  