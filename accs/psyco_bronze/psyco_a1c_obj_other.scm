	� G   w�       �VmoE��H����.wg'*�Hn���تT!d��{�����:�)�zNK�*��TUE�h�"��R��_X�J���g;	-���+�{f���ggg=�o34cԲF��6*~��U'"!+��!~H#�:�шM��������`w�(:�T�fiph @�TN2bU8,�q�Q[�4`�rWO�d<�t��U���XG��d�cVm�Mz�
c�%��σv�P�0�vky˒�'�]ۋ�_>�D-�HP��s+��"�J9kڎ�a�sh���}lv�b#v�l	�4�,}�2
Eb�u��e�i㽬��+Y���УM�'�OS��A�n��iƦ��|�>�����:����O������.xW#qG[8�r2R�D��e�2
��W:���.�^�f�؎p�Vt���ȂXy�T��h�r)�3�}Z�����2�]��E���)��<�����0��i��J�g6�H0��^�mj���<��V�Z[j��]h�( �	�ٍ5hKW�
h�p<@�R�4vo���o>��}�|	���Ó�?����'�x�)���C>H~��� �!t���<�"2G~Oe4TVp7�nC�{S�%��_.h���6k�ep1�$�)?ף6C�Sx�&\��x�
@$G$��o��2�Dڹ��/���p�bm-����ytZ�`���bd�xt�ɷ�������GF�@����`���/ �t�����xr�>��W<���x�!X�6��n)y�d]�-�6�hN&^:b۲#b7<|��,�'��X��3�J�LZ��}yB M�t�Ls�}tgG�N[Kh�z��XրI�!�q�T�Ü��Z湉^-J�)�dRׅA���ӕ�f�v:0/�=���j�RVQ�X(�:*Z֘3_P��|��E�i;l�azZ�%;�����K���W	P�"_ۄ�N�Y�Vo4�c�b����2�Ū�|h#-�Fô4Q?�8m�t���+��F��z�&��r��s(�6�rΟ�O��=TT��4�=��g��=O��%0�+g��Z�]�/�V�ϭ�V�V�ז�tV�l̟9SXD�g��I�睐�x��:�������2������������!�����Lh��!q��
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
 � � �-            �X� �D /accs/psyco_bronze/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� D
�\                        �  