	� G   t�       �VmoE��H����.wg'*7Hn���تT!��{�����ڍU"���UQ��*
�hB|�x�/�%����s���+�{f���ggg=�)��8j&�喬��-lU�7k#1��� aLG�)#V��5�;��i�,��8��C<tyG<q���X��#|�_��&�q�p��D.�^g��P'op��&���$��Z�m�k@ȱQ�>�HҮĈ��	�2�1v��(:�r<A�A��1"j�3I�&/,�@V�G𵹼�z^b�Iߣk-��ND|l%^����&�cN;V�lB�M�UMk��N^o�W���8�M�u���:	A�n��Y��-�b���^l�9����Hw�?��6x� I�fFAE�]u(�Y@�!�CQ7*G��F A�O���|�'|QB��΢�,���s�����#[n"�yN��kC�c��{]���oJ�hs�q��C�@�g.�4Ψ�D�����"��_q�F�83O(�UY��׼مa��u�M�^�C8��VB���Wr���}{�����S�>���x"�v��w�~�@���]�>���;:Bg
�̓ 2s�uF#@g�w+�6��?�^a��Ւ�^���ZZ �IXA��u9��һ�%�� Dq0%טO���i�S���V��o��M��:�v�g� ���@'�~�������~��������d�?�=��3 �?އ����"�+��K���;"}�Y��^u��Y�>ِu[e4����\F�F��9��PE�$2f˦�
�lY+�ˊ��/(�f�I�=ܽ�woK��[Kj�t��X0��T!�qYׇÜ��[��D/�Ø�p�Q߇)kȦ3�����:0�d=n��Z�R^�ǚ)�&*;Έ�X҂�|���ԓ�7�$=�J<7�r�l*��n��^ۄ'^�y���h�G(����^Q�rޏ媢z�h�,�2	�IZ�'���w�6�:s����U��յ�ҥ�R�`�����(�!R;ujG7PY�1ۖ7�k��J��"m��d��_~c�~um~��ra��8���<of�c�ܹ�,zW=��tNҞv<6����+�]9����P���Ͽ���� ���H�?�)��7�	�C�
   f  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_gre/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            �X� �D /accs/psyco_gre/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� D
��                        �  