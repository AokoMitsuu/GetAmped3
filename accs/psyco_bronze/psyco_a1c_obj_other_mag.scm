	� G   w�       �VmoE��H����.wg'V�\#�Ih"hZb�R��u>�ً�n/wk7�X�9--��RUUU�Q�@@�K��a�(����$��_P����gf�������pЌQ�.1�[ب�!nV����tj��!��PF#b4mfs�[��ςݭ��4�3A�u��� S9ɈU�4��G��(i"�"�宝��x��1��5u����,��'Ƭڶ��*2�ƸK�+�1�0�4a�8��%Oл��|��Z8���F��W�!+�#�j9kڎ�a�wh���}lv�b#v�l	�4�,}�2
Ebt��e�i㽬�+Y��УM�'�OS��A�n��iƦɇ;|����o�>����1OvG����xW#qG=i9)s���2�Q]�+��y�
~?c�ulG8f+�u�edA��^)�\��t�����>�e��O�t΂�.X��t�K�x���L�h�R���cj%�s�D$zv�
�6�|~a�P
j�D��Z�v�$
�m�mv�bZ��U��4���1̓�[���F����O���`���w���?|����<�����&�]�U	��:UXn� �#��2*��q�������h��4�������2�M唟�Q�!�)��.�XG<` �#�rM��7Hi�D"��q�Xix8h����QX�<:�r0Mk~����xt�ɷO��������ֿ/������_ 8������?��>��'_����ɇ`M�Xd����M�uɖ��(�9�x鈭gG�nx��<4Y�Om��,g.���xA��@��&��h����m�:m-���sS`Y&]���eSs�j�&:]�S�����VM�+�͘�t`(^N{\G��6�����PtuT��1g����(N���v���<K(vl��3�z5m��<-_ۄ�N�Y�Vo4�c�b����2�Ū�|h#-�Fô4Q?�8m�t���+��F��z�&��r��s(�6�rΟ�O��=TT��4�=��g���@��%0�+���Z�]�/�V�ϯ�V�V�ז�tV�l̟=[XD�g��I����x��:�������2������������!�����Lh�����|�
   f  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_bronze/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            ��� �D /accs/psyco_bronze/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� D
��                        �  