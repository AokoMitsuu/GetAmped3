	� G   s�       �VmkG�,C��V�rr�w'�"���Ʀ��Z"J1�Ӟ����yo�X����4)��BM4�--m]J�����.2�����I���u��OEB�{f���gg�n�^�8j&�喬��-lU�7k#1��#aLG�)#V��5���4|�FG!^���� ���IE�I��>��FIƸQ�vj,P���q��78�b��,��7���v��*rl��O"���A 1"�q�i��$1������n� ���Z8�LR����K�Z#��L�v=/���ѕ��Jv'">�/�WPL�1'�T6!҆����(/�7��Y�z�&���G�Is���`7��,_���b���Yl�>����Hw����6x� I�5̌�����P65� C4��nT�|�@�������vN���.A�EYk�7f$C!c�F��DZ��rՆ�G�+����ߔ,j��h�4K����\�i�Q���j-�K[D&n��6�bqj S
j��֚^�~��E@�V�6�|�-��:[	�Z^@#^�������{��[OE�@��@wp����o?<����}�~!6����L���� �!t���<�"3GaOg4 tV9p��n�{#��_)��.o[˥9p�����P�#�)�{C.�8�<` ��)�F|��4G�L�p��VZ�Z�mL�4���lۙ�.�l{���D����w�OwD�����{�C�ߗ��p���W ���a�/EzGl~*�oEzCl��'`��Xf����͒�ɺ��*�	�x�m�e�m��\UDH"c�lJ�`O����xI��@�6�M����[�v�ZR��K�F��L�
��˪>,��5�2'&:]V#�����c֐Mgj�˹�u`$^�z�D�6����5U�MTv�g����(O��Gi;n�QzF�$�xn����Pj5�#@xZݶ	O�n��o���P���+�����UEu�Z�e���2P?�xm�uf�(��T�����ź��`��M���(�)\<ulϠr6���Nඒ:�@��'0����[�_Y�]�.�����.,-Κٜ��X<{�4�>V�2\��HO8�vp�����8���?P���/�[� �Z8|���7aC�=	b��
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_pur2/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            ��� �D /accs/psyco_pur2/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ��                        �  